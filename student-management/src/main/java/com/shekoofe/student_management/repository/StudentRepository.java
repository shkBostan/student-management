package com.shekoofe.student_management.repository;

import com.shekoofe.student_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Student entity.
 * Provides CRUD operations and JPA query methods.
 *
 * @author s Bostan
 * @since Aug, 2025
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}
