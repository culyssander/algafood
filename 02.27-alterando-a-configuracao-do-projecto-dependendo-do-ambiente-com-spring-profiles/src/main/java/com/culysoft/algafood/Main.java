package com.culysoft.algafood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Main {

    @Autowired
    private Properties properties;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public String hello() {
        System.out.println("Porta: " + properties.getHostServidor());
        System.out.println("Host: " + properties.getPortaServidor());
        return "Hello";
    }
}
