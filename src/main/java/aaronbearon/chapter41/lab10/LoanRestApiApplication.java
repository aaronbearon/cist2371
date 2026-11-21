package aaronbearon.chapter41.lab10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/aaronbearon/chapter41/lab10/application.properties")
public class LoanRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanRestApiApplication.class, args);
    }

}
