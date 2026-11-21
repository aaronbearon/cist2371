package aaronbearon.chapter41.lesson10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/aaronbearon/chapter41/lesson10/application.properties")
public class StudentRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentRestApiApplication.class, args);
    }

}
