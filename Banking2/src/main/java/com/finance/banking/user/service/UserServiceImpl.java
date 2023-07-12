package com.finance.banking.user.service;

import com.finance.banking.user.dao.UserRepository;
import com.finance.banking.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> find(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser;
    }

    @Override
    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }
}
