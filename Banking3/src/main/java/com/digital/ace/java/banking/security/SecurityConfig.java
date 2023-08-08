package com.digital.ace.java.banking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//
//        //since we defined our users here,
//        //Spring Boot will not use the user/password from the application.properties file
//        UserDetails john = User.builder()
//                .username("john").password("{noop}test123")
//                .roles("TELLER")
//                .build();
//
//        UserDetails mary = User.builder()
//                .username("mary").password("{noop}test123")
//                .roles("TELLER", "MANAGER")
//                .build();
//
//        UserDetails susan = User.builder()
//                .username("susan").password("{noop}test123")
//                .roles("TELLER", "MANAGER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(john, mary, susan);
//    }

    @Bean
    //Spring Security Custom Tables
    //06-setup-spring-security-demo-database-bcrypt-custom-table-names.sql
    //fun123
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        //tell Spring Security to use JDBC authentication with our data source
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, email_address, active from bank_users where username=?");

        //define query to retrieve the authorities/roles by user
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, role from bank_roles where username=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/users").hasRole("TELLER")
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasRole("TELLER")
                        //.requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        //.requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        //.requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        //use HTTP Basic authentication
        httpSecurity.httpBasic(Customizer.withDefaults());

        //disable Cross Site Request Forgery (CSRF)
        //in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }
}
