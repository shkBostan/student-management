package com.shekoofe.student_management.mapper;

import com.shekoofe.student_management.dto.StudentDTO;
import com.shekoofe.student_management.model.Student;

/**
 *
 *<p>Mapper for converting between Student entity and StudentDTO.</p>
 *
 * @author s Bostan
 * @since Aug, 2025
 */
public class StudentMapper {
    public static StudentDTO toDto(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getMajor(),
                student.getAge()
        );
    }

    public static Student toEntity(StudentDTO dto) {
        return new Student(
                dto.id(),
                dto.name(),
                dto.major(),
                dto.age()
        );
    }
}
