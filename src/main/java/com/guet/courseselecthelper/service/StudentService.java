package com.guet.courseselecthelper.service;

import com.guet.courseselecthelper.entity.Student;
import com.guet.courseselecthelper.entity.vo.StudentDto;

import java.util.List;

public interface StudentService {
    void insertStudentInfo(String tableName, Student student);
    Student findById(String tableName, String account);
    String findByAccount(String tableName, String account);

    List<StudentDto> getAllStudent();
}
