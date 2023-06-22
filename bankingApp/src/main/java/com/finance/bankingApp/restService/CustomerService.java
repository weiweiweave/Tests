package com.finance.bankingApp.restService;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private Long customerId;

    private String lastName;

    private String firstName;

    public CustomerService() {}

    public CustomerService(Long customerId, String lastName, String firstName) {
        this.customerId = customerId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public CustomerService(String lastName) {

        this.lastName = lastName;

    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
