package aaronbearon.chapter39.lesson07.repository;

import aaronbearon.chapter39.lesson07.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByLastNameContainingIgnoreCase(String lastName);
}
