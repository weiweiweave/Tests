package com.finance.banking.customer.mapper;

import com.finance.banking.customer.dto.CustomerDTO;
import com.finance.banking.customer.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();

        return new CustomerDTO(firstName,lastName);
    }
}
