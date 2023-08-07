package com.digital.ace.java.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BankingApplication {

	//private static final Logger log = LoggerFactory.getLogger(BankingAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}

	//	@Bean
//	public CommandLineRunner demo(CustomerRepository repository) {
//		return (args) -> {
//			// save a few customers
//			repository.save(new Customer("Jack", "Bauer"));
//			repository.save(new Customer("Chloe", "O'Brian"));
//			repository.save(new Customer("Kim", "Bauer"));
//			repository.save(new Customer("David", "Palmer"));
//			repository.save(new Customer("Michelle", "Dessler"));
//
//			// fetch all customers
//			log.info("Customers found with findAll():");
//			log.info("-------------------------------");
//			for (Customer customer : repository.findAll()) {
//				log.info(customer.toString());
//			}
//			log.info("");
//		};
//	}

}
