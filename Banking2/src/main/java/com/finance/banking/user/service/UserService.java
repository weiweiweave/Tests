package com.finance.banking.user.service;

import com.finance.banking.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> findAll();

    User save(User user);

    Optional<User> find(Long id);
}
