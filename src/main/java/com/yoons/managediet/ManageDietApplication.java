package com.yoons.managediet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ManageDietApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageDietApplication.class, args);
    }

}
