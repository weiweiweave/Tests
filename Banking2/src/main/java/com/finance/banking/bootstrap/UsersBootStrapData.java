package com.finance.banking.bootstrap;

import com.finance.banking.user.dao.UserRepository;
import com.finance.banking.user.entity.User;
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

    private UserRepository userRepository;

    @Value("${sample.users}")
    String usersPath;

    private final Logger logger = LoggerFactory.getLogger(UsersBootStrapData.class);

    public UsersBootStrapData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Spring Boot invokes its run() method after it starts the context and before the application starts.
    @Override
    public void run(String... args) throws Exception {

            List<User> csvToBean = new CsvToBeanBuilder(new FileReader(usersPath)).withType(User.class).build().parse();

            Iterator<User> csvUserIterator = csvToBean.iterator();

            while (csvUserIterator.hasNext()) {
                User csvUser = csvUserIterator.next();

                User newUser = new User(csvUser.getUsername(), csvUser.getPassword(), csvUser.getEmailAddress(), LocalDateTime.now());

                userRepository.save(newUser);
            }

    }
}