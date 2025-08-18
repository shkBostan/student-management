package com.shekoofe.student_management.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
/**
 * Created on Aug, 2025
 *
 * @author s Bostan
 * <p>Unit tests for {@link com.shekoofe.student_management.controller.StudentController}.</p>
 * <p> This test class uses MockMvc to simulate HTTP requests and validate
 *  CRUD operations (GET, POST, PUT, DELETE) for the StudentController.</p>
 */

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test GET /api/students endpoint.
     * Verifies HTTP 200 OK response.
     */
    @Test
    void testGetAllStudents() throws Exception {
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk());
    }

    /**
     * Test POST /api/students endpoint.
     * Sends a JSON student object and verifies it is saved correctly.
     */
    @Test
    void testAddStudent() throws Exception {
        String studentJson = """
                {
                    "name": "shekoofe",
                    "major": "Computer Science",
                    "age": 25
                }
                """;

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("shekoofe"))
                .andExpect(jsonPath("$.major").value("Computer Science"))
                .andExpect(jsonPath("$.age").value(25));
    }

    /**
     * Test PUT /api/students/{id} endpoint.
     * Updates an existing student and verifies the update is applied.
     */
    @Test
    void testUpdateStudent() throws Exception {
        // First, create a student to update
        String studentJson = """
                {
                    "name": "Old Name",
                    "major": "Math",
                    "age": 20
                }
                """;

        String response = mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Extract the ID from the response JSON
        Number idNumber = JsonPath.read(response, "$.id");
        Long id = idNumber.longValue();

        // Prepare updated JSON
        String updatedJson = """
                {
                    "name": "New Name",
                    "major": "Physics",
                    "age": 22
                }
                """;

        mockMvc.perform(put("/api/students/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Name"))
                .andExpect(jsonPath("$.major").value("Physics"))
                .andExpect(jsonPath("$.age").value(22));
    }

    /**
     * Test DELETE /api/students/{id} endpoint.
     * Deletes an existing student and verifies it no longer exists.
     */
    @Test
    void testDeleteStudent() throws Exception {
        // First, create a student to delete
        String studentJson = """
                {
                    "name": "To Delete",
                    "major": "History",
                    "age": 23
                }
                """;

        String response = mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Number idNumber = JsonPath.read(response, "$.id");
        Long id = idNumber.longValue();

        // Perform DELETE
        mockMvc.perform(delete("/api/students/{id}", id))
                .andExpect(status().isOk());

        // Verify student is deleted
        mockMvc.perform(get("/api/students/{id}", id))
                .andExpect(status().is4xxClientError()); // e.g., 404 Not Found
    }
}
