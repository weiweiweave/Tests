package com.digital.ace.java.banking.customer.mapper;

import com.digital.ace.java.banking.customer.dto.CreateCustomerRequest;
import com.digital.ace.java.banking.customer.dto.CustomerDTO;
import com.digital.ace.java.banking.customer.entity.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();

        return new CustomerDTO(firstName,lastName);
    }

    public static Customer toCustomer(CreateCustomerRequest customerRequest) {
        LocalDate joinedDate = LocalDate.parse(customerRequest.getJoinedDate());
        LocalDate dateOfBirth = LocalDate.parse(customerRequest.getDateOfBirth());

        return new Customer(
                customerRequest.getUuid(),
                customerRequest.getStaffIdWhoKeyIn(),
                customerRequest.getCompany(),
                customerRequest.getFundSource(),
                customerRequest.getAddress(),
                customerRequest.getCity(),
                joinedDate,
                customerRequest.getNric(),
                customerRequest.getFirstName(),
                customerRequest.getLastName(),
                customerRequest.getSex(),
                customerRequest.getEmailAddress(),
                customerRequest.getPhone(),
                dateOfBirth,
                customerRequest.getJobTitle(),
                LocalDateTime.now());
    }
}
