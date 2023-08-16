package com.digital.ace.java.banking.role.service;

import com.digital.ace.java.banking.role.dao.RoleRepository;
import com.digital.ace.java.banking.role.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

//    @Override
//    public List<Employee> findAll() {
//        return userRepository.findAll();
//    }
//
//    public Optional<Employee> find(Long id) {
//        Optional<Employee> optionalUser = userRepository.findById(id);
//        return optionalUser;
//    }

    @Override
    public Role save(Role role) {
        return roleRepository.saveAndFlush(role);
    }
}
