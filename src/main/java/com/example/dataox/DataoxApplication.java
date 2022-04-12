package com.example.dataox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.dataox.service.LiftService.lift;

@SpringBootApplication
public class DataoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataoxApplication.class, args);
        lift();
    }

}
