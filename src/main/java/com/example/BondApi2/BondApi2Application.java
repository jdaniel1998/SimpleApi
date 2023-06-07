package com.example.BondApi2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BondApi2Application {

	public static void main(String[] args) {
		SpringApplication.run(BondApi2Application.class, args);
	}

}
