package org.foo.sandbox.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MainSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainSpringBootApplication.class, args);
    }
}
