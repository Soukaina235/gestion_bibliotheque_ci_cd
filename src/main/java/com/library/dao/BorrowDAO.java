
package com.library.dao;

import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.util.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAO {
    public List<Borrow> getAllBorrows() {
        List<Borrow> borrows = new ArrayList<>();
        String query = "SELECT * FROM borrows";
        try (Connection connection = DbConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query);
        ) {
            BookDAO bookDAO = new BookDAO();
            StudentDAO studentDAO = new StudentDAO();
            while (rs.next()) {
                Student student = studentDAO.getStudentById(rs.getInt("student_id"));
                Book book = bookDAO.getBookById(rs.getInt("book_id"));

                Borrow borrow = new Borrow(
                        student,
                        book,
                        rs.getDate("borrow_date"),
                        rs.getDate("return_date")
                );
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }

//    public boolean save (Borrow borrow) {
//        String query = "INSERT INTO borrows (student_id, book_id, borrow_date, return_date) VALUES (?, ?, ?, ?)";
//        String isBookAvailableQuery = "SELECT id_available FROM books WHERE id = ?";
//        String doesUserExist = "SELECT COUNT(id) FROM users WHERE id = ?";
//        String updateBookQuery = "UPDATE books SET is_available = ? WHERE id = ?";
//        try (Connection connection = DbConnection.getConnection();
//             PreparedStatement stmt = connection.prepareStatement(query);
//             PreparedStatement isBookAvailableStmt = connection.prepareStatement(isBookAvailableQuery);
//             PreparedStatement doesUserExistStmt = connection.prepareStatement(doesUserExist);
//             PreparedStatement updateBook = connection.prepareStatement(updateBookQuery)
//        ) {
//            isBookAvailableStmt.setInt(1, borrow.getBook().getId());
//
//            stmt.setInt(1, borrow.getStudent().getId());
//            stmt.setInt(2, borrow.getBook().getId());
//            stmt.setDate(3, new java.sql.Date(borrow.getBorrowDate().getTime()));
//            stmt.setDate(4,
//                    borrow.getReturnDate() == null ? null :
//                    new java.sql.Date(borrow.getReturnDate().getTime())
//            );
//            stmt.executeUpdate();
//
//            updateBook.setBoolean(1, false);
//            updateBook.setInt(2, borrow.getBook().getId());
//            updateBook.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }

    public String save(Borrow borrow) {
        String doesUserExistQuery = "SELECT COUNT(id) FROM students WHERE id = ?";
        String doesBookExistQuery = "SELECT COUNT(id) FROM books WHERE id = ?";
        String isBookAvailableQuery = "SELECT is_available FROM books WHERE id = ?";
        String query = "INSERT INTO borrows (student_id, book_id, borrow_date, return_date) VALUES (?, ?, ?, ?)";
        String updateBookQuery = "UPDATE books SET is_available = ? WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement doesUserExistStmt = connection.prepareStatement(doesUserExistQuery);
             PreparedStatement doesBookExistStmt = connection.prepareStatement(doesBookExistQuery);
             PreparedStatement isBookAvailableStmt = connection.prepareStatement(isBookAvailableQuery);
             PreparedStatement insertBorrowStmt = connection.prepareStatement(query);
             PreparedStatement updateBookStmt = connection.prepareStatement(updateBookQuery)) {

            // Verifier si étudiant et livre existent
            doesUserExistStmt.setInt(1, borrow.getStudent().getId());
            ResultSet userResult = doesUserExistStmt.executeQuery();
            doesBookExistStmt.setInt(1, borrow.getBook().getId());
            ResultSet bookResult = doesBookExistStmt.executeQuery();

            if ((userResult.next() && userResult.getInt(1) == 0) ||
                            (bookResult.next() && bookResult.getInt(1) == 0)
            ) {
                return "Étudiant ou livre non trouvé.";
            }

            // Verifier si livre est disponible
            isBookAvailableStmt.setInt(1, borrow.getBook().getId());
            ResultSet isBookAvailable = isBookAvailableStmt.executeQuery();
            if (isBookAvailable.next() && !isBookAvailable.getBoolean("is_available")) {
                return "Le livre n'est pas disponible.";
            }

            // Insertion du borrow
            insertBorrowStmt.setInt(1, borrow.getStudent().getId());
            insertBorrowStmt.setInt(2, borrow.getBook().getId());
            insertBorrowStmt.setDate(3, new java.sql.Date(borrow.getBorrowDate().getTime()));
            insertBorrowStmt.setDate(4,
                    borrow.getReturnDate() == null ? null :
                            new java.sql.Date(borrow.getReturnDate().getTime()));
            insertBorrowStmt.executeUpdate();

            // modification de is_available
            updateBookStmt.setBoolean(1, false);
            updateBookStmt.setInt(2, borrow.getBook().getId());
            updateBookStmt.executeUpdate();

            return "Livre emprunté avec succès!";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Erreur lors de l'insertion du borrow.";
        }
    }

}
