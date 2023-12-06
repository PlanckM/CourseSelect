package com.guet.courseselecthelper.service;

import com.guet.courseselecthelper.entity.Student;

public interface StudentService {
    void insertStudentInfo();
    Student findById(String tableName, String account);
    String findByAccount(String tableName, String account);
}
