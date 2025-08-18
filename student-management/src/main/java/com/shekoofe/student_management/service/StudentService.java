package com.shekoofe.student_management.service;

import com.shekoofe.student_management.exception.ResourceNotFoundException;
import com.shekoofe.student_management.model.Student;
import com.shekoofe.student_management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for Student entity.
 * Encapsulates business logic and communicates with StudentRepository.
 * Provides methods to find, save, update, and delete students.
 *
 * @author s Bostan
 * @since Aug, 2025
 */

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    /**
     * Get all students.
     * @return List of Student objects
     */
    public List<Student> findAll() { return repository.findAll(); }

    /**
     * Find a student by ID.
     * @param id Student ID
     * @return Student object
     * @throws ResourceNotFoundException if student not found
     */
    public Student findById(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id)); }

    /**
     * Save or update a student.
     * @param student Student object
     * @return Saved Student object
     */
    public Student save(Student student) { return repository.save(student); }

    /**
     * Delete a student by ID.
     * @param id Student ID
     */
    public void delete(Long id) { repository.deleteById(id); }
}
