package com.library;

import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.service.StudentService;
import com.library.util.DbConnection;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class StudentServiceTest {
    private StudentService studentService;
    private StudentDAO studentDAO;

    @BeforeEach
    void setUp() throws SQLException {
        studentDAO = new StudentDAO();
        studentService = new StudentService();
    }

    @AfterAll
    @BeforeAll
    public static void tearDown() {
        System.out.println("Test teardown: Clearing database...");
        try (Connection connection = DbConnection.getConnection();
             Statement statement = connection.createStatement()) {

            // Disable foreign key checks (if needed)
            statement.execute("SET FOREIGN_KEY_CHECKS = 0");

            // Truncate all tables
            statement.execute("TRUNCATE TABLE borrows");
            statement.execute("TRUNCATE TABLE books");
            statement.execute("TRUNCATE TABLE students");

            // Re-enable foreign key checks
            statement.execute("SET FOREIGN_KEY_CHECKS = 1");

            System.out.println("Database cleared successfully!");
        } catch (SQLException e) {
            System.err.println("Error clearing database: " + e.getMessage());
        }
    }

    @Test
    @Order(1)
    void testAddStudent() {
        Student student = new Student(1, "Jane Doe", "janedoe@mail.com");
        studentService.addStudent(student);
        assertEquals(1, studentDAO.getAllStudents().size());
        assertEquals("Jane Doe", studentDAO.getStudentById(1).getName());
    }

    @Test
    @Order(2)
    void testUpdateStudent() {
        Student studentupdated = new Student(1, "Alice Smith", "alice.smith@example.com");
        studentService.updateStudent(studentupdated);
        assertEquals("Alice Smith", studentDAO.getStudentById(1).getName());
    }

    @Test
    @Order(3)
    void testDeleteStudent() {
        studentService.deleteStudent(1);
        assertNull(studentDAO.getStudentById(1));
    }

    @Test
    @Order(4)
    void testGetAllStudents() {
        Student student1 = new Student(2, "Alice", "alice@example.com");
        Student student2 = new Student(3, "Bob", "bob@example.com");
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        assertEquals(2, studentDAO.getAllStudents().size());
    }
}
