package com.example.school.service;

import com.example.school.model.Student;
import com.example.school.model.StudentRowMapper;
import com.example.school.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class StudentH2Service implements StudentRepository {

    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM student";
    private static final String SELECT_STUDENT_BY_ID = "SELECT * FROM student WHERE studentId = ?";
    private static final String INSERT_STUDENT = "INSERT INTO student (studentName, gender, standard) VALUES (?, ?, ?)";
    private static final String UPDATE_STUDENT = "UPDATE student SET studentName = ?, gender = ?, standard = ? WHERE studentId = ?";
    private static final String DELETE_STUDENT = "DELETE FROM student WHERE studentId = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentRowMapper studentRowMapper;

    @Override
    public List<Student> getAllStudents() {
        return jdbcTemplate.query(SELECT_ALL_STUDENTS, studentRowMapper);
    }

    @Override
    public Student getStudentById(int studentId) {
        return jdbcTemplate.queryForObject(SELECT_STUDENT_BY_ID, studentRowMapper, studentId);
    }

    @Override
    public Student addStudent(Student student) {
        jdbcTemplate.update(INSERT_STUDENT, student.getStudentName(), student.getGender(), student.getStandard());
        int generatedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        student.setStudentId(generatedId);
        return student;
    }

    @Override
    public void addStudents(List<Student> students) {
        List<Object[]> batchArgs = new ArrayList<>();
        for (Student student : students) {
            Object[] args = {student.getStudentName(), student.getGender(), student.getStandard()};
            batchArgs.add(args);
        }
        jdbcTemplate.batchUpdate(INSERT_STUDENT, batchArgs);
    }

    @Override
    public Student updateStudent(int studentId, Student student) {
        jdbcTemplate.update(UPDATE_STUDENT, student.getStudentName(), student.getGender(), student.getStandard(), studentId);
        student.setStudentId(studentId);
        return student;
    }

    @Override
    public void deleteStudent(int studentId) {
        jdbcTemplate.update(DELETE_STUDENT, studentId);
    }
}
