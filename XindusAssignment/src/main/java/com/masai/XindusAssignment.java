package com.masai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class XindusAssignment {

	public static void main(String[] args) {
		SpringApplication.run(XindusAssignment.class, args);
	}
	
	@PostConstruct
    public void init() {
        System.out.println("Inside init");
    }

}
