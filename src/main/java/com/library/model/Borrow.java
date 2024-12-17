package com.library.model;

import java.util.Date;

public class Borrow {
    private Student student;
    private Book book;
    private Date borrowDate;
    private Date returnDate;

    public Borrow(Student student, Book book, Date borrowDate, Date returnDate) {
        this.student = student;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Borrow(Student student, Book book) {
        this.student = student;
        this.book = book;
        this.borrowDate = new Date();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "student=" + student.getName() +
                ", book=" + book.getTitle() +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
