package com.finance.banking.user.service;

import com.finance.banking.user.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    User save(User user);
}
