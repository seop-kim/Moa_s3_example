package com.moa.MoaS3Example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
public class MoaS3ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoaS3ExampleApplication.class, args);
    }

}
