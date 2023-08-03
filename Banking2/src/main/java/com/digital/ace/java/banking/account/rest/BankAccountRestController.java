package com.digital.ace.java.banking.account.rest;


import com.digital.ace.java.banking.account.dto.BankAccountDTO;
import com.digital.ace.java.banking.account.dto.BankAccountIdDTO;
import com.digital.ace.java.banking.account.dto.CreateBankAccountRequest;
import com.digital.ace.java.banking.account.entity.BankAccount;
import com.digital.ace.java.banking.account.mapper.BankAccountMapper;
import com.digital.ace.java.banking.account.service.BankAccountService;
import com.digital.ace.java.banking.customer.entity.Customer;
import com.digital.ace.java.banking.customer.service.CustomerService;
import com.digital.ace.java.banking.exception.ItemNotFoundException;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class BankAccountRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private BankAccountService bankAccountService;

    private CustomerService customerService;

    @Autowired
    public BankAccountRestController(BankAccountService bankAccountService, CustomerService customerService) {
        this.bankAccountService = bankAccountService;
        this.customerService = customerService;
    }

    @PostMapping("/bank_account")
    public BankAccountIdDTO createBankAccount(@RequestBody @Valid CreateBankAccountRequest createBankAccountRequest)  throws Exception {

        Optional<Customer> optionalCustomer = customerService.findByUUID(createBankAccountRequest.getUuid());

        if (optionalCustomer.isPresent()) {
            BankAccount bankAccount = BankAccountMapper.toBankAccount(createBankAccountRequest);

            bankAccount.setId(Long.valueOf(0));

            //logger.trace(user.toString());

            BankAccount savedBankAccount = bankAccountService.save(bankAccount);

            //logger.trace(savedUser.getId().toString());

            return new BankAccountIdDTO(savedBankAccount.getId());
        }
        else {
            throw new ItemNotFoundException(createBankAccountRequest.getUuid() + " is not present.");
        }


    }

    @GetMapping("/bank_accounts")
    public List<BankAccountDTO> listBankAccounts() {
        List<BankAccount> bankAccountList = bankAccountService.findAll();
        //logger.trace(userList.toString());
        List<BankAccountDTO> bankAccountDTOList = bankAccountList.stream().map(BankAccountMapper::toDTO).collect(Collectors.toList());
        //logger.trace(userDTOList.toString());
        return bankAccountDTOList;
    }

//    @GetMapping("/bankAccount/{account_no}")
//    public UserDTO getUser(@PathVariable("account_no") String account_no) throws ItemNotFoundException {
//
//        Optional<User> optionalUser = userService.find(id);
//        User user = new User();
//
//        if(optionalUser.isPresent()) {
//            user = optionalUser.get();
//        }
//        else {
//            throw new ItemNotFoundException(id);
//        }
//        return UserMapper.toDTO(user);
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
