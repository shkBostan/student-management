package com.shekoofe.student_management.controller;

import com.shekoofe.student_management.exception.ResourceNotFoundException;
import com.shekoofe.student_management.model.Student;
import com.shekoofe.student_management.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * REST Controller for managing students.
 * Provides CRUD operations and a simple HTML view of students.
 *
 * Base URL: /api/students
 *
 * @author s Bostan
 * @since Aug, 2025
 */
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    /**
     * Get all students.
     * @return List of Student objects
     */
    @GetMapping
    public List<Student> getAllStudents() { return service.findAll(); }

    /**
     * Get a single student by ID.
     * @param id Student ID
     * @return Student object
     * @throws ResourceNotFoundException if student not found
     */
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) { return service.findById(id); }

    /**
     * Create a new student.
     * @param student Student object from request body
     * @return Saved Student object
     */
    @PostMapping
    public Student addStudent(@Valid @RequestBody Student student) { return service.save(student); }

    /**
     * Update an existing student.
     * @param id Student ID
     * @param student Student object from request body
     * @return Updated Student object
     */
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        return service.save(student);
    }

    /**
     * Delete a student by ID.
     * @param id Student ID
     */
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) { service.delete(id); }

    /**
     * Handle ResourceNotFoundException and return HTTP 404.
     * @param ex Exception
     * @return ResponseEntity with error message
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    /**
     * Simple HTML view of all students.
     * @return HTML string
     */
    @GetMapping("/view")
    @ResponseBody
    public String viewAllStudents() {
        List<Student> students = service.findAll();

        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Students</title></head><body>");
        html.append("<h1>Student List</h1>");
        html.append("<table border='1' style='border-collapse: collapse;'>");
        html.append("<tr><th>ID</th><th>Name</th><th>Major</th><th>Age</th></tr>");

        for (Student s : students) {
            html.append("<tr>")
                    .append("<td>").append(s.getId()).append("</td>")
                    .append("<td>").append(s.getName()).append("</td>")
                    .append("<td>").append(s.getMajor()).append("</td>")
                    .append("<td>").append(s.getAge()).append("</td>")
                    .append("</tr>");
        }

        html.append("</table></body></html>");
        return html.toString();
    }
}
