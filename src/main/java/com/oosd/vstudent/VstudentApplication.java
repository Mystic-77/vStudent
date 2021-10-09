package com.oosd.vstudent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
public class VstudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(VstudentApplication.class, args);
    }

}
