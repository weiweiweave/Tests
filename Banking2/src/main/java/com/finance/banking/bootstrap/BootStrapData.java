package com.finance.banking.bootstrap;

import com.finance.banking.user.dao.UserRepository;
import com.finance.banking.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    private UserRepository userRepository;

    private static final String SAMPLE_CSV_FILE_PATH = "src/main/resources/data.csv";

    private final Logger logger = LoggerFactory.getLogger(BootStrapData.class);

    public BootStrapData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

            List<User> csvToBean = new CsvToBeanBuilder(new FileReader(SAMPLE_CSV_FILE_PATH)).withType(User.class).build().parse();

            Iterator<User> csvUserIterator = csvToBean.iterator();

            while (csvUserIterator.hasNext()) {
                User csvUser = csvUserIterator.next();

                userRepository.save(new User(csvUser.getUsername(), csvUser.getPassword(), csvUser.getEmailAddress()));
            }

    }
}