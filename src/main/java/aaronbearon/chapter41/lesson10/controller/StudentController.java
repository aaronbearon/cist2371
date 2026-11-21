package aaronbearon.chapter41.lesson10.controller;

import aaronbearon.chapter41.lesson10.model.Student;
import aaronbearon.chapter41.lesson10.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepository repo;

    @GetMapping
    public List<Student> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Student getOne(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Student create(@RequestBody Student s) {
        return repo.save(s);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student updated) {
        Student s = repo.findById(id).orElseThrow(NullPointerException::new);
        s.setName(updated.getName());
        s.setMajor(updated.getMajor());
        return repo.save(s);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "Student deleted!";
    }
}
