
package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.dao.BorrowDAO;
import com.library.model.Borrow;

import java.sql.SQLException;

public class BorrowService {

    private BorrowDAO borrowDAO;
    private BookDAO bookDAO;
    private StudentDAO studentDAO;

    public BorrowService() throws SQLException {
        this.borrowDAO = new BorrowDAO();
        this.bookDAO = new BookDAO();
        this.studentDAO = new StudentDAO();
    }

    public String borrowBook(Borrow borrow) {
        return borrowDAO.borrowBook(borrow);
    }

    public void displayBorrows() {
        System.out.println("Liste des emprunts...");
        for (Borrow borrow : borrowDAO.getAllBorrows()) {
            System.out.println(borrow);
        }
    }

    public String returnBook(Borrow borrow) {
        return borrowDAO.returnBook(borrow);
    }
}
