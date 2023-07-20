package com.digital.ace.java.banking.customer.mapper;

import com.digital.ace.java.banking.customer.dto.CustomerDTO;
import com.digital.ace.java.banking.customer.entity.Customer;
import com.digital.ace.java.banking.user.dto.UserDTO;
import com.digital.ace.java.banking.user.entity.User;

public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();

        return new CustomerDTO(firstName,lastName);
    }
}
