package com.digital.ace.java.banking.bootstrap;

import com.digital.ace.java.banking.employee.entity.Employee;
import com.digital.ace.java.banking.role.entity.Role;
import com.digital.ace.java.banking.role.service.RoleService;
import com.digital.ace.java.banking.employee.dto.CreateEmployeeDTO;
import com.digital.ace.java.banking.employee.service.EmployeeService;
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
public class EmployeesBootStrapData implements CommandLineRunner {

    private EmployeeService userService;

    private RoleService roleService;

    //inject properties for sample.users
    @Value("${sample.employees}")
    String usersPath;

    private final Logger logger = LoggerFactory.getLogger(EmployeesBootStrapData.class);

    public EmployeesBootStrapData(EmployeeService userService, RoleService roleService) {

        this.userService = userService;
        this.roleService = roleService;
    }

    //Spring Boot invokes its run() method after it starts the context and before the application starts.
    @Override
    public void run(String... args) throws Exception {

            List<CreateEmployeeDTO> csvToBean = new CsvToBeanBuilder(new FileReader(usersPath)).withType(CreateEmployeeDTO.class).build().parse();

            Iterator<CreateEmployeeDTO> csvUserIterator = csvToBean.iterator();

            while (csvUserIterator.hasNext()) {
                CreateEmployeeDTO csvUser = csvUserIterator.next();

                Employee newEmployee = new Employee(
                        csvUser.getUsername(),
                        csvUser.getPassword(),
                        csvUser.getEmailAddress(),
                        Integer.valueOf(csvUser.getActive()),
                        LocalDateTime.now());

                Employee savedEmployee = userService.save(newEmployee);

                String s = csvUser.getRoles();
                String[] stringArray = s.split(",");
                for(int i=0; i< stringArray.length; i++) {
                    //prints the tokens
                    //System.out.println(stringArray[i]);
                    Role newRole = new Role();
                    newRole = new Role(savedEmployee.getUsername(),stringArray[i]);
                    roleService.save(newRole);
                }
            }

    }
}