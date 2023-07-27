package com.digital.ace.java.banking.bootstrap;

import com.digital.ace.java.banking.user.dto.CreateUserDTO;
import com.digital.ace.java.banking.user.entity.User;
import com.digital.ace.java.banking.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Order(value=1)
@Component
public class UsersBootStrapData implements CommandLineRunner {

    private UserService userService;

    //inject properties for sample.users
    @Value("${sample.users}")
    String usersPath;

    private final Logger logger = LoggerFactory.getLogger(UsersBootStrapData.class);

    public UsersBootStrapData(UserService userService) {
        this.userService = userService;
    }

    //Spring Boot invokes its run() method after it starts the context and before the application starts.
    @Override
    public void run(String... args) throws Exception {

            List<CreateUserDTO> csvToBean = new CsvToBeanBuilder(new FileReader(usersPath)).withType(CreateUserDTO.class).build().parse();

            Iterator<CreateUserDTO> csvUserIterator = csvToBean.iterator();

            while (csvUserIterator.hasNext()) {
                CreateUserDTO csvUser = csvUserIterator.next();

                User newUser = new User(
                        csvUser.getUsername(),
                        csvUser.getPassword(),
                        csvUser.getEmailAddress(),
                        LocalDateTime.now());

                userService.save(newUser);
            }

    }
}