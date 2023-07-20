package com.digital.ace.java.banking.customer.mapper;

import com.digital.ace.java.banking.customer.dto.CreateCustomerDTO;
import com.digital.ace.java.banking.customer.dto.CustomerDTO;
import com.digital.ace.java.banking.customer.entity.Customer;
import com.digital.ace.java.banking.user.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();

        return new CustomerDTO(firstName,lastName);
    }

//    public static User toCustomer(CreateCustomerDTO createCustomerDTO) {
//        UUID uuid = UUID.randomUUID();
//        return new Customer(
//                uuid.toString(),
//                createCustomerDTO.getUsername(), createUserDTO.getPassword(), createUserDTO.getEmail(), LocalDateTime.now());
//    }
}
