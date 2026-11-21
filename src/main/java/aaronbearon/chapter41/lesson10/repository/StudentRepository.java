package aaronbearon.chapter41.lesson10.repository;

import aaronbearon.chapter41.lesson10.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
