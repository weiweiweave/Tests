package com.digital.ace.java.banking.transaction.rest;


import com.digital.ace.java.banking.account.entity.BankAccount;
import com.digital.ace.java.banking.account.service.BankAccountService;
import com.digital.ace.java.banking.exception.InsufficientBalanceException;
import com.digital.ace.java.banking.exception.ItemNotFoundException;
import com.digital.ace.java.banking.transaction.dto.BankTransactionDTO;
import com.digital.ace.java.banking.transaction.dto.BankTransactionIdDTO;
import com.digital.ace.java.banking.transaction.dto.CreateBankTransactionRequest;
import com.digital.ace.java.banking.transaction.entity.BankTransaction;
import com.digital.ace.java.banking.transaction.mapper.BankTransactionMapper;
import com.digital.ace.java.banking.transaction.service.BankTransactionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class BankTransactionRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private BankTransactionService bankTransactionService;

    private BankAccountService bankAccountService;

    @Autowired
    public BankTransactionRestController(BankTransactionService bankTransactionService, BankAccountService bankAccountService) {
        this.bankTransactionService = bankTransactionService;
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/bank_transaction")
    public BankTransactionIdDTO createBankTransaction(@RequestBody @Valid CreateBankTransactionRequest createBankTransactionRequest)  throws Exception {

        Optional<BankAccount> optionalBankAccount = bankAccountService.findByAccountNo(createBankTransactionRequest.getAccountNo());
        Double amount= Double.parseDouble(createBankTransactionRequest.getAmount());
        Boolean isCredit = Boolean.valueOf(createBankTransactionRequest.getIsCredit());
        BankAccount bankAccount;
        BankTransaction savedBankTransaction;

        if (optionalBankAccount.isPresent()) {
            bankAccount = optionalBankAccount.get();

            BankTransaction newBankTransaction = new BankTransaction(
                    createBankTransactionRequest.getUuid(),
                    createBankTransactionRequest.getStaffIdWhoKeyIn(),
                    createBankTransactionRequest.getAccountNo(),
                    amount,
                    isCredit,
                    createBankTransactionRequest.getRemarks(),
                    LocalDateTime.now(),
                    bankAccount);

            //logger.trace(savedUser.getId().toString());

            if (isCredit) {
                savedBankTransaction = bankTransactionService.save(newBankTransaction);
                // to deposit to account
                bankAccountService.deposit(createBankTransactionRequest.getAccountNo(),amount);
            }
            else {
                // to withdraw from account
                Double currentBalance = bankAccount.getBalance();
                if (currentBalance>=amount) {
                    savedBankTransaction = bankTransactionService.save(newBankTransaction);
                    bankAccountService.withdrawal(createBankTransactionRequest.getAccountNo(),amount);
                }
                else {
                    throw new InsufficientBalanceException("This account " + createBankTransactionRequest.getAccountNo() + " has insufficient balance.");
                }
            }

            return new BankTransactionIdDTO(savedBankTransaction.getId());
        }
        else {
            throw new ItemNotFoundException(createBankTransactionRequest.getAccountNo() + " is not present.");
        }

    }

    @GetMapping("/bank_transactions")
    public List<BankTransactionDTO> listBankTransactions() {
        List<BankTransaction> bankTransactionList = bankTransactionService.findAll();
        //logger.trace(userList.toString());
        List<BankTransactionDTO> bankTransactionDTOList = bankTransactionList.stream().map(BankTransactionMapper::toDTO).collect(Collectors.toList());
        //logger.trace(userDTOList.toString());
        return bankTransactionDTOList;
    }

//    @GetMapping("/bankAccount/{account_no}")
//    public EmployeeDTO getUser(@PathVariable("account_no") String account_no) throws ItemNotFoundException {
//
//        Optional<Employee> optionalUser = userService.find(id);
//        Employee user = new Employee();
//
//        if(optionalUser.isPresent()) {
//            user = optionalUser.get();
//        }
//        else {
//            throw new ItemNotFoundException(id);
//        }
//        return EmployeeMapper.toDTO(user);
//    }

    //Controller Based Exception Handling
    //to handle exceptions thrown by request handling (@RequestMapping)
    //methods in the same controller.
    //Handle exceptions without the @ResponseStatus annotation
    // (typically predefined exceptions that you didn't write)
//    @ExceptionHandler(NoSuchElementException.class)
//    public @ResponseBody ExceptionJSONInfo handleNoSuchElementException(HttpServletRequest request, Exception ex) {
//
//        ExceptionJSONInfo response = new ExceptionJSONInfo();
//        response.setUrl(request.getRequestURL().toString());
//        response.setMessage(ex.getMessage());
//
//        return response;
//    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public @ResponseBody ExceptionJSONInfo handleValidationExceptions(
//            HttpServletRequest request,
//            MethodArgumentNotValidException ex) {
//
//        ExceptionJSONInfo response = new ExceptionJSONInfo();
//        response.setUrl(request.getRequestURL().toString());
//
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        response.setMessage(errors.toString());
//
//        return response;
//    }
}
