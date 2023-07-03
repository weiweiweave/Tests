package com.finance.banking.customer.mapper;

import com.finance.banking.customer.dto.CreateCustomerDTO;
import com.finance.banking.customer.dto.CustomerDTO;
import com.finance.banking.customer.entity.Customer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        String address = customer.getAddress();

        return new CustomerDTO(firstName,lastName,address);
    }

    public Customer toCustomer(CreateCustomerDTO createCustomerDTO) {
        return new Customer(createCustomerDTO.getFirstName(), createCustomerDTO.getLastName(), createCustomerDTO.getAddress());
    }
}
