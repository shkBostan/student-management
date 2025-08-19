package com.shekoofe.student_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
/**
 *
 * <p>DTO for Student entity (Java 17 record).
 * Immutable and concise.</p>
 *
 * @author s Bostan
 * @since Aug, 2025
 *
 */
public record StudentDTO(
    Long id,
    @NotBlank String name,
    String major,
    @Min(18) int age
){}
