package com.guet.courseselecthelper.demos.service;

import com.guet.courseselecthelper.demos.entity.Student;

public interface StudentService {
    void insertStudentInfo();
    Student findById(String tableName, String account);
    String findByAccount(String tableName, String account);
}
