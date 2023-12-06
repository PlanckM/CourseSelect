package com.guet.courseselecthelper;

import com.guet.courseselecthelper.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class testLogin {
    @Resource
    private StudentService studentService;
    @Test
    void TestLogin(){
        studentService.findByAccount("student", "1");
    }
}
