package com.library.service;
import com.library.dao.BookDAO; // Importation de BookDAO
import com.library.model.Book;   // Importation de Book
import java.util.List;


public class BookService {
    private BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }

    public void addBook(Book book) {
        bookDAO.add(book);
    }

    public void displayBooks() {
        List<Book> books = bookDAO.getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book findBookById(int id) {
        return bookDAO.getBookById(id);
    }

    public Book findBookByIsbn(String isbn) {
        return bookDAO.getBookByIsbn(isbn);
    }

    public void deleteBook(int id) {
        bookDAO.delete(id);
    }

    public void updateBook(Book book) {
        bookDAO.update(book);
    }
}
