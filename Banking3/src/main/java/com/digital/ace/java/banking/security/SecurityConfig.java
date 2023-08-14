package com.digital.ace.java.banking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    //Spring Security Custom Tables
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        //tell Spring Security to use JDBC authentication with our data source
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, active from bank_users where username=?");

        //define query to retrieve the authorities/roles by user
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, role from bank_roles where username=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    SecurityFilterChain appSecurity(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        /**
         *  Below is the custom security configurations
         */
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(mvc.pattern("/api/users"),mvc.pattern("/api/users/**")).authenticated()
                                .requestMatchers(mvc.pattern("/api/notices"),mvc.pattern("/api/contact")).permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
