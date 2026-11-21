package aaronbearon.chapter39.dukesbookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

// We will add the database during the final project.
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@PropertySource("classpath:/aaronbearon/chapter39/dukesbookstore/application.properties")
public class DukesBookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(DukesBookstoreApplication.class, args);
    }

}
