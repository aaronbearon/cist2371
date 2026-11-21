package aaronbearon.chapter39.lab07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

/**
 * Aaron Blum, CIST 2373 Java 3, Lab 7, Compute Loan Application.
 * <p>
 * The shared project classpath includes JPA and MySQL for other apps, but this one has no database,
 * so those auto-configurations are excluded.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@PropertySource("classpath:/aaronbearon/chapter39/lab07/application.properties")
public class ComputeLoanApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComputeLoanApplication.class, args);
    }
}
