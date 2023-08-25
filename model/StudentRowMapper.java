package com.example.school.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        int studentId = rs.getInt("studentId");
        String studentName = rs.getString("studentName");
        String gender = rs.getString("gender");
        int standard = rs.getInt("standard");
        return new Student(studentId, studentName, gender, standard);
    }
}
