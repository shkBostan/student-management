package com.shekoofe.student_management.controller;

import com.shekoofe.student_management.dto.StudentDTO;
import com.shekoofe.student_management.exception.ResourceNotFoundException;
import com.shekoofe.student_management.mapper.StudentMapper;
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

    /** Get all students. */
    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return service.findAll().stream()
                .map(StudentMapper::toDto)
                .toList(); // Java 16+
    }

    /** Get a single student by ID. */
    @GetMapping("/{id}")
    public StudentDTO getStudent(@PathVariable Long id) {
        return StudentMapper.toDto(service.findById(id));
    }

    /** Create a new student. */
    @PostMapping
    public StudentDTO addStudent(@Valid @RequestBody StudentDTO dto) {
        Student saved = service.save(StudentMapper.toEntity(dto));
        return StudentMapper.toDto(saved);
    }

    /** Update an existing student. */
    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody StudentDTO dto) {
        Student student = StudentMapper.toEntity(dto);
        student.setId(id);
        Student updated = service.save(student);
        return StudentMapper.toDto(updated);
    }

    /** Delete a student by ID. */
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        service.delete(id);
    }

    /** Handle ResourceNotFoundException and return HTTP 404. */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /** Simple HTML view of all students. */
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