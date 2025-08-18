package com.shekoofe.student_management.exception;

/**
 * Created on Aug, 2025
 *
 * @author s Bostan
 * <p>Custom exception thrown when a requested resource is not found.</p>
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
