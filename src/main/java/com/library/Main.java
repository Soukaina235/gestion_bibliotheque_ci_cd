package com.library;

import com.library.service.BorrowService;
import com.library.service.BookService;
import com.library.service.StudentService;
import com.library.model.Book;
import com.library.model.Student;
import com.library.model.Borrow;
import com.library.dao.BorrowDAO;  // Importer BorrowDAO

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        // Création des services
        BookService bookService = new BookService();
        StudentService studentService = new StudentService();
        BorrowService borrowService = new BorrowService();

        Student student = new Student("John Doe", "johndoe@mail.com");
        Book book = new Book("Effective Java", "Joshua Bloch", "123456", 2017, true);

        bookService.addBook(book);
        studentService.addStudent(student);

        book = bookService.findBookByIsbn("123456");
        student = studentService.findStudentByEmail("johndoe@mail.com");
        Borrow borrow = new Borrow(student, book, new Date(), new Date());

        borrowService.borrowBook(borrow);
        
        boolean running = true;

        while (running) {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Afficher les livres");
            System.out.println("3. Ajouter un étudiant");
            System.out.println("4. Afficher les étudiants");
            System.out.println("5. Emprunter un livre");
            System.out.println("6. Afficher les emprunts");
            System.out.println("7. Quitter");

            System.out.print("Choisir une option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consommer la ligne restante après l'entier

            switch (choice) {
                case 1:
                    System.out.print("Entrez le titre du livre: ");
                    String title = scanner.nextLine();
                    System.out.print("Entrez l'auteur du livre: ");
                    String author = scanner.nextLine();
                    Book new_book = new Book(title, author);
                    bookService.addBook(new_book);
                    break;

                case 2:
                    bookService.displayBooks();
                    break;

                case 3:
                    System.out.print("Entrez le nom de l'étudiant: ");
                    String studentName = scanner.nextLine();
                    Student new_student = new Student(studentName);
                    studentService.addStudent(new_student);
                    break;

                case 4:
                    studentService.displayStudents();
                    break;

                case 5:
                    System.out.print("Entrez l'ID de l'étudiant: ");
                    int studentId = scanner.nextInt();
                    System.out.print("Entrez l'ID du livre: ");
                    int bookId = scanner.nextInt();
                    Student studentForBorrow = studentService.findStudentById(studentId);
                    Book bookForBorrow = bookService.findBookById(bookId);
                    if (studentForBorrow != null && bookForBorrow != null) {
                        // Créer un objet Borrow avec les informations nécessaires
                        Borrow new_borrow = new Borrow(studentForBorrow, bookForBorrow, new Date(), null);
                        borrowService.borrowBook(new_borrow);  // Appel de la méthode avec l'objet Borrow
                    } else {
                        System.out.println("Étudiant ou livre introuvable.");
                    }
                    break;

                case 6:
                    borrowService.displayBorrows();
                    break;

                case 7:
                    running = false;
                    System.out.println("Au revoir!");
                    break;

                default:
                    System.out.println("Option invalide.");
            }
        }

        scanner.close();
    }
}
