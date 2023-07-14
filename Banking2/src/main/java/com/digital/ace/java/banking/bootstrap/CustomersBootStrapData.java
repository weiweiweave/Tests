package com.digital.ace.java.banking.bootstrap;

import com.digital.ace.java.banking.customer.dao.CustomerRepository;
import com.digital.ace.java.banking.customer.dto.CreateCustomerDTO;
import com.digital.ace.java.banking.customer.entity.Customer;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

            List<CreateCustomerDTO> csvToBean = new CsvToBeanBuilder(new FileReader(customersPath)).withType(CreateCustomerDTO.class).build().parse();

            Iterator<CreateCustomerDTO> csvCustomerIterator = csvToBean.iterator();

            while (csvCustomerIterator.hasNext()) {
                CreateCustomerDTO csvCustomer = csvCustomerIterator.next();

                LocalDate csvJoinedDate = LocalDate.parse(csvCustomer.getJoinedDate());
                LocalDate csvDateOfBirth = LocalDate.parse(csvCustomer.getDateOfBirth());

                Customer newCustomer = new Customer(
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

                customerRepository.save(newCustomer);
            }

    }
}