package com.finance.banking.bootstrap;

import com.finance.banking.customer.dao.CustomerRepository;
import com.finance.banking.customer.entity.Customer;
import com.finance.banking.user.entity.User;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

@Order(value=2)
@Component
public class CustomersBootStrapData implements CommandLineRunner {

    private CustomerRepository customerRepository;

    @Value("${sample.customers}")
    String customersPath;

    private final Logger logger = LoggerFactory.getLogger(CustomersBootStrapData.class);

    public CustomersBootStrapData(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Spring Boot invokes its run() method after it starts the context and before the application starts.
    @Override
    public void run(String... args) throws Exception {

            List<Customer> csvToBean = new CsvToBeanBuilder(new FileReader(customersPath)).withType(Customer.class).build().parse();

            Iterator<Customer> csvCustomerIterator = csvToBean.iterator();

            while (csvCustomerIterator.hasNext()) {
                Customer csvCustomer = csvCustomerIterator.next();

                customerRepository.save(new Customer(csvCustomer.getCompany(),csvCustomer.getFirstName(), csvCustomer.getLastName(), csvCustomer.getAddress(), csvCustomer.getEmailAddress()));
            }

    }
}