package com.digital.ace.java.banking.config;

import com.digital.ace.java.banking.employee.dao.EmployeeRepository;
import com.digital.ace.java.banking.employee.entity.Employee;
import com.digital.ace.java.banking.role.dao.RoleRepository;
import com.digital.ace.java.banking.role.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankUserDetails implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private EmployeeRepository employeeRepository;

    private RoleRepository roleRepository;

    @Autowired
    public BankUserDetails(EmployeeRepository employeeRepository, RoleRepository roleRepository){
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password;
        List<GrantedAuthority> authorities;
        List<Employee> employee = employeeRepository.findByUsername(username);
        List<Role> roles = roleRepository.findByUsername(username);
        if (employee.size() == 0) {
            throw new UsernameNotFoundException("Employee details not found for the user : " + username);
        } else{
            userName = employee.get(0).getUsername();
            password = employee.get(0).getPassword();
            authorities = new ArrayList<>();
            if (roles.size() == 0) {
                throw new UsernameNotFoundException("Role details not found for the user : " + username);
            } else {
                for(Role role : roles) {
                    authorities.add(new SimpleGrantedAuthority(role.getRole()));
                }

            }
        }
        //logger.info(userName);
        //logger.info(password);
        //logger.info(authorities.toString());
        return new User(userName,password,authorities);
    }
}
