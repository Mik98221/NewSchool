package com.example.school.repository;

import java.util.*;

import com.example.school.model.Student;
public interface StudentRepository {
    List<Student> getAllStudents();
    Student getStudentById(int studentId);
    Student addStudent(Student student);
    void addStudents(List<Student> students);
    Student updateStudent(int studentId, Student student);
    void deleteStudent(int studentId);
}