package com.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


//@ImportResource("/integration/integration.xml")
@EnableScheduling
@SpringBootApplication
@ComponentScan({"com.example.domain", "com.example.parser", "com.example.services", "com.example.repos", "com.example.parser"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
