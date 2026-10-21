package aaronbearon.examples.studentmanagement;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StudentManagementApplication {
    private static final String packagePath = StudentManagementApplication.class.getPackageName().replace('.', '/');

    public static void main(String[] args) {
        new SpringApplicationBuilder(StudentManagementApplication.class)
                .properties("spring.config.location=classpath:/" + packagePath + "/application.properties")
                .run(args);
    }
}
