package com.library;

import com.library.dao.BookDAO;
import com.library.dao.StudentDAO;
import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.service.BorrowService;
import com.library.util.DbConnection;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class BorrowServiceTest {
    static BorrowService borrowService;
    static BookDAO bookDAO;
    static StudentDAO studentDAO;

    @BeforeAll
    static void setUp() throws SQLException {
        bookDAO = new BookDAO();
        studentDAO = new StudentDAO();
        borrowService = new BorrowService();

        // Ajouter un étudiant
        studentDAO.addStudent(new Student(1, "Alice", "alice@example.com"));
        studentDAO.addStudent(new Student(2, "Bob", "bob@example.com"));

        // Ajouter des livres
        bookDAO.add(new Book(1, "Java Programming", "John Doe", "B123", 2015, true));
        bookDAO.add(new Book(2, "Advanced Java", "Jane Doe", "B987", 2019, true));
    }

    @BeforeAll
    @AfterAll
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
    void testBorrowBook() {
        Student student = studentDAO.getStudentById(1);
        Book book = bookDAO.getBookById(1);
        Borrow borrow = new Borrow(student, book);
        String result = borrowService.borrowBook(borrow);
        assertEquals("Livre emprunté avec succès!", result);
        assertFalse(bookDAO.getBookById(1).isAvailable());
    }

//    @Test
//    void testReturnBook() {
//        borrowService.borrowBook(1, 1);
//        assertEquals("Livre retourné avec succès!", borrowService.returnBook(1, 1));
//        assertTrue(bookDAO.getBookById(1).get().isAvailable());
//    }

    @Test
    @Order(2)
    void testBorrowBookNotAvailable() {
        Student student = studentDAO.getStudentById(1);
        Book book = bookDAO.getBookById(1);
        Borrow borrow = new Borrow(student, book);
        borrowService.borrowBook(borrow);
        String result = borrowService.borrowBook(borrow);
        assertEquals("Le livre n'est pas disponible.", result);
    }

//    @Test
//    @Order(3)
//    void testBorrowBookStudentNotFound() {
//        Student student = studentDAO.getStudentById(1);
//        Book book = bookDAO.getBookById(4);
//        Borrow borrow = new Borrow(student, book);
//        String result = borrowService.borrowBook(borrow);
//        assertEquals("Étudiant ou livre non trouvé.", result);
//    }
}
