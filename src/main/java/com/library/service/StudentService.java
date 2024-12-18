package com.library.service;

import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.util.DbConnection;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private StudentDAO studentDAO;

    public StudentService() throws SQLException {
        this.studentDAO = new StudentDAO();
    }

    public void addStudent(Student student) {
        try {
            studentDAO.addStudent(student);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout de l'étudiant : " + e.getMessage());
        }
    }

    public void displayStudents() {
        try {
            List<Student> students = studentDAO.getAllStudents();
            for (Student student : students) {
                System.out.println("ID: " + student.getId() + " | Nom: " + student.getName());
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'affichage des étudiants : " + e.getMessage());
        }
    }

    public Student findStudentById(int id) {
        try {
            return studentDAO.getStudentById(id);
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche de l'étudiant par ID : " + e.getMessage());
        }
        return null;
    }

    public Student findStudentByEmail(String email) {
        return studentDAO.getStudentByEmail(email);
    }

    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }

    public void deleteStudent(int id) {
        studentDAO.deleteStudent(id);
    }
}
