package com.digital.ace.java.banking.bootstrap;

import com.digital.ace.java.banking.account.dto.CreateAccountTypeDTO;
import com.digital.ace.java.banking.account.dto.CreateBankAccountDTO;
import com.digital.ace.java.banking.account.entity.AccountType;
import com.digital.ace.java.banking.account.entity.BankAccount;
import com.digital.ace.java.banking.account.entity.SavingsAccount;
import com.digital.ace.java.banking.account.service.AccountTypeService;
import com.digital.ace.java.banking.account.service.BankAccountService;
import com.digital.ace.java.banking.account.service.SavingsAccountService;
import com.digital.ace.java.banking.customer.dto.CreateCustomerDTO;
import com.digital.ace.java.banking.customer.entity.Customer;
import com.digital.ace.java.banking.customer.service.CustomerService;
import com.digital.ace.java.banking.employee.dto.CreateEmployeeDTO;
import com.digital.ace.java.banking.employee.entity.Employee;
import com.digital.ace.java.banking.employee.service.EmployeeService;
import com.digital.ace.java.banking.exception.InsufficientBalanceException;
import com.digital.ace.java.banking.exception.ItemNotFoundException;
import com.digital.ace.java.banking.role.entity.Role;
import com.digital.ace.java.banking.role.service.RoleService;
import com.digital.ace.java.banking.transaction.dto.CreateSavingDepositTransactionDTO;
import com.digital.ace.java.banking.transaction.entity.BankTransaction;
import com.digital.ace.java.banking.transaction.service.BankTransactionService;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Order(value=1)
@ConditionalOnProperty(
        prefix = "bootstrap",
        name = "enabled",
        havingValue = "true")
@Component
public class BankingBootStrapData implements CommandLineRunner {

    private EmployeeService employeeService;

    private RoleService roleService;

    //inject properties for sample.users
    @Value("${sample.employees}")
    String employeesPath;

    private CustomerService customerService;

    //inject properties for sample.customers
    @Value("${sample.customers}")
    String customersPath;

    private AccountTypeService accountTypeService;

    //inject properties for sample.accountType
    @Value("${sample.accountType}")
    String accountTypePath;

    private BankAccountService bankAccountService;

    private SavingsAccountService savingsAccountService;

    //inject properties for sample.savingDeposit
    @Value("${sample.savingDeposit}")
    String savingDepositPath;

    //inject properties for sample.savingDepositTransactions
    @Value("${sample.savingDepositTransactions}")
    String savingDepositTransactionsPath;

    private BankTransactionService bankTransactionService;

    private final Logger logger = LoggerFactory.getLogger(BankingBootStrapData.class);

    public BankingBootStrapData(EmployeeService userService,
                                RoleService roleService,
                                CustomerService customerService,
                                AccountTypeService accountTypeService,
                                BankAccountService bankAccountService,
                                SavingsAccountService savingsAccountService,
                                BankTransactionService bankTransactionService) {

        this.employeeService = userService;
        this.roleService = roleService;
        this.customerService = customerService;
        this.accountTypeService = accountTypeService;
        this.bankAccountService = bankAccountService;
        this.savingsAccountService = savingsAccountService;
        this.bankTransactionService = bankTransactionService;
    }

    //Spring Boot invokes its run() method after it starts the context and before the application starts.
    @Override
    public void run(String... args) throws Exception {
        EmployeesBootStrapData();
        CustomersBootStrapData();
        AccountTypeBootStrapData();
        SavingsBootStrapData();
        SavingsDepositTransactionsBootStrapData();
    }

    private void EmployeesBootStrapData() throws FileNotFoundException {
        List<CreateEmployeeDTO> csvToBean = new CsvToBeanBuilder(new FileReader(employeesPath)).withType(CreateEmployeeDTO.class).build().parse();

        Iterator<CreateEmployeeDTO> csvUserIterator = csvToBean.iterator();

        while (csvUserIterator.hasNext()) {
            CreateEmployeeDTO csvUser = csvUserIterator.next();

            Employee newEmployee = new Employee(
                    csvUser.getUsername(),
                    csvUser.getPassword(),
                    csvUser.getEmailAddress(),
                    csvUser.getActive(),
                    LocalDateTime.now());

            Employee savedEmployee = employeeService.save(newEmployee);

            String s = csvUser.getRoles();
            String[] stringArray = s.split(",");
            for(int i=0; i< stringArray.length; i++) {
                //prints the tokens
                //System.out.println(stringArray[i]);
                Role newRole = new Role();
                newRole = new Role(savedEmployee.getUsername(),stringArray[i]);
                roleService.save(newRole);
            }
        }
    }

    private void CustomersBootStrapData() throws FileNotFoundException {
        List<CreateCustomerDTO> csvToBean = new CsvToBeanBuilder(new FileReader(customersPath)).withType(CreateCustomerDTO.class).build().parse();

        Iterator<CreateCustomerDTO> csvCustomerIterator = csvToBean.iterator();

        while (csvCustomerIterator.hasNext()) {
            CreateCustomerDTO csvCustomer = csvCustomerIterator.next();

            LocalDate csvJoinedDate = LocalDate.parse(csvCustomer.getJoinedDate());
            LocalDate csvDateOfBirth = LocalDate.parse(csvCustomer.getDateOfBirth());

            Customer newCustomer = new Customer(
                    csvCustomer.getUuid(),
                    csvCustomer.getStaffIdWhoKeyIn(),
                    csvCustomer.getCompany(),
                    csvCustomer.getFundSource(),
                    csvCustomer.getAddress(),
                    csvCustomer.getCity(),
                    csvJoinedDate,
                    csvCustomer.getNric(),
                    csvCustomer.getFirstName(),
                    csvCustomer.getLastName(),
                    csvCustomer.getSex(),
                    csvCustomer.getEmailAddress(),
                    csvCustomer.getPhone(),
                    csvDateOfBirth,
                    csvCustomer.getJobTitle(),
                    LocalDateTime.now());

            customerService.save(newCustomer);
        }
    }

    private void AccountTypeBootStrapData() throws FileNotFoundException {
        List<CreateAccountTypeDTO> csvToBean = new CsvToBeanBuilder(new FileReader(accountTypePath)).withType(CreateAccountTypeDTO.class).build().parse();

        Iterator<CreateAccountTypeDTO> csvUserIterator = csvToBean.iterator();

        while (csvUserIterator.hasNext()) {
            CreateAccountTypeDTO csvAccountType = csvUserIterator.next();

            AccountType newAccountType = new AccountType(
                    csvAccountType.getAccountDescription());

            accountTypeService.save(newAccountType);
        }
    }

    private void SavingsBootStrapData() throws FileNotFoundException {
        List<CreateBankAccountDTO> csvToBean = new CsvToBeanBuilder(new FileReader(savingDepositPath)).withType(CreateBankAccountDTO.class).build().parse();

        Iterator<CreateBankAccountDTO> csvBankAccountIterator = csvToBean.iterator();

        while (csvBankAccountIterator.hasNext()) {
            CreateBankAccountDTO csvBankAccount = csvBankAccountIterator.next();

            //logger.trace(csvBankAccount.toString());

            LocalDate csvCreatedDate = LocalDate.parse(csvBankAccount.getCreatedDate());
            Double csvInterestRate = Double.parseDouble(csvBankAccount.getInterestRate());
            Double csvMinAmountToCalInterest = Double.parseDouble(csvBankAccount.getMinAmountToCalInterest());

            SavingsAccount newSavingsAccount = new SavingsAccount(
                    csvInterestRate,
                    csvMinAmountToCalInterest);

            //logger.trace(newSavingsAccount.toString());

            SavingsAccount savedSavingsAccount = savingsAccountService.save(newSavingsAccount);

            //logger.trace(savedSavingsAccount.toString());

            BankAccount newBankAccount = new BankAccount(
                    csvBankAccount.getUuid(),
                    csvBankAccount.getStaffIdWhoKeyIn(),
                    csvCreatedDate,
                    csvBankAccount.getCustomerNric(),
                    Double. parseDouble(csvBankAccount.getBalance()),
                    csvBankAccount.getAccountNo(),
                    Long.valueOf(1),
                    savedSavingsAccount.getId(),
                    LocalDateTime.now());

            //logger.trace(newBankAccount.toString());

            bankAccountService.save(newBankAccount);
        }
    }

    private void SavingsDepositTransactionsBootStrapData() throws FileNotFoundException {
        List<CreateSavingDepositTransactionDTO> csvToBean = new CsvToBeanBuilder(new FileReader(savingDepositTransactionsPath)).withType(CreateSavingDepositTransactionDTO.class).build().parse();

        Iterator<CreateSavingDepositTransactionDTO> csvSavingDepositTransactionIterator = csvToBean.iterator();

        while (csvSavingDepositTransactionIterator.hasNext()) {
            CreateSavingDepositTransactionDTO csvCreateSavingDepositTransaction = csvSavingDepositTransactionIterator.next();

            Double csvAmount= Double.parseDouble(csvCreateSavingDepositTransaction.getAmount());
            Boolean csvIsCredit = Boolean.valueOf(csvCreateSavingDepositTransaction.getIsCredit());
            BankAccount csvBankAccount;

            Optional<BankAccount> optionalBankAccount = bankAccountService.findByAccountNo(csvCreateSavingDepositTransaction.getAccountNo());

            if (optionalBankAccount.isPresent()) {
                csvBankAccount = optionalBankAccount.get();

                BankTransaction newBankTransaction = new BankTransaction(
                        csvCreateSavingDepositTransaction.getUuid(),
                        csvCreateSavingDepositTransaction.getStaffIdWhoKeyIn(),
                        csvCreateSavingDepositTransaction.getAccountNo(),
                        csvAmount,
                        csvIsCredit,
                        csvCreateSavingDepositTransaction.getRemarks(),
                        LocalDateTime.now(),
                        csvBankAccount);

                if (csvIsCredit) {
                    bankTransactionService.save(newBankTransaction);
                    // to deposit to account
                    bankAccountService.deposit(csvCreateSavingDepositTransaction.getAccountNo(),csvAmount);
                }
                else {
                    // to withdraw from account
                    Double currentBalance = csvBankAccount.getBalance();
                    if (currentBalance>=csvAmount) {
                        bankTransactionService.save(newBankTransaction);
                        bankAccountService.withdrawal(csvCreateSavingDepositTransaction.getAccountNo(),csvAmount);
                    }
                    else {
                        throw new InsufficientBalanceException("This account " + csvCreateSavingDepositTransaction.getAccountNo() + " has insufficient balance.");
                    }
                }
            }
            else {
                throw new ItemNotFoundException(csvCreateSavingDepositTransaction.getAccountNo() + " is not present.");
            }
        }
    }
}
