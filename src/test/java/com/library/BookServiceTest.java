package com.library;

import com.library.dao.BookDAO;
import com.library.model.Book;
import com.library.service.BookService;
import com.library.util.DbConnection;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class BookServiceTest {

    private BookService bookService;
    private BookDAO bookDAO;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
        bookDAO = new BookDAO();
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
    void testAddBook() {
        Book book = new Book(1, "Java Programming", "John Doe", "B123", 2015, true);

        bookService.addBook(book);

        assertEquals(1, bookDAO.getAllBooks().size());
        assertEquals("Java Programming", bookDAO.getBookById(1).getTitle());
    }

    @Test
    @Order(2)
    void testFindBookById() {
        int bookId = 2;
        Book book = new Book(bookId, "C++ Programming", "Jane Doe", "B234", 2010, true);
        bookService.addBook(book);

        Book foundBook = bookService.findBookById(bookId);
        assertNotNull(foundBook);
   }

    @Test
    @Order(3)
    void testFindBookByIsbn() {
        String isbn = "1234567890";
        Book book = new Book(3, "C Programming", "Jane Doe", isbn, 2010, true);
        bookService.addBook(book);

        Book foundBook = bookService.findBookByIsbn(isbn);
        assertNotNull(foundBook);
    }
}
