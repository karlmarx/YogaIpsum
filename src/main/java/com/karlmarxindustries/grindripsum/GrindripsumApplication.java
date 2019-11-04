package com.karlmarxindustries.grindripsum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrindripsumApplication {
@Autowired
    public static void main(String[] args) {
        SpringApplication.run(GrindripsumApplication.class, args);

    }

}
