package com.batch.testapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TestApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(TestApiApplication.class, args);
    }

}
