package aaronbearon.examples.studentmanagement.controller;

import aaronbearon.examples.studentmanagement.model.Student;
import aaronbearon.examples.studentmanagement.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Handler:
    // Display all student or search by last name:
    @GetMapping("/")
    public String listStudents(@RequestParam(required = false) String lastName, Model model) {
        List<Student> students;
        if (lastName != null && !lastName.isBlank()) {
            students = studentRepository.findByLastNameContainingIgnoreCase(lastName);
        } else {
            students = studentRepository.findAll();
        }
        model.addAttribute("students", students);
        model.addAttribute("lastName", lastName);
        return "students";
    }

    // Display the form for adding a student:
    @GetMapping("/students/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // Save a new student:
    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "student-form";
        }

        studentRepository.save(student);
        return "redirect:/";
    }

    // Display the form for editing a student:
    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
        model.addAttribute("student", student);
        return "student-form";
    }

    // Update an existing student:
    @PostMapping("/students/update/{id}")
    public String updateStudent(@PathVariable Long id, @Valid @ModelAttribute("student") Student formStudent, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            formStudent.setId(id);
            model.addAttribute("student", formStudent);
            return "student-form";
        }

        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
        existingStudent.setFirstName(formStudent.getFirstName());
        existingStudent.setLastName(formStudent.getLastName());
        existingStudent.setEmail(formStudent.getEmail());
        studentRepository.save(existingStudent);
        return "redirect:/";
    }

    // Delete a student:
    @PostMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "redirect:/";
    }
}
