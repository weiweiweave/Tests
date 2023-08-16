package com.digital.ace.java.banking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

@Configuration
public class SecurityConfig {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

        @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    SecurityFilterChain appSecurity(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("TELLER")
                                .requestMatchers(HttpMethod.GET, "/api/employee/**").hasRole("TELLER")
                                .requestMatchers(HttpMethod.POST, "/api/employee/**").hasRole("MANAGER")
                                //.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("TELLER")

                                //.requestMatchers("/api/employees","/user/**").authenticated()
                                //.requestMatchers("/notices","/contact","/register").permitAll()

                                //.requestMatchers(mvc.pattern("/api/users"),mvc.pattern("/api/users/**")).authenticated()
                                //.requestMatchers(mvc.pattern("/api/notices"),mvc.pattern("/api/contact")).permitAll()
                                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
