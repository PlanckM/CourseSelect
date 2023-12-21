package com.guet.courseselecthelper.controller;
import com.guet.courseselecthelper.common.entity.Result;
import org.springframework.http.HttpEntity;

import com.guet.courseselecthelper.entity.Student;
import com.guet.courseselecthelper.service.StudentService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("{tableName}/{rowKey}")
    public Student getStudentById(@PathVariable String tableName, @PathVariable String rowKey) {
        return studentService.findById(tableName, rowKey);
    }

    @GetMapping("/list")
    public Result getAllStudent(){
        return Result.success(studentService.getAllStudent());
    }
    @PostMapping("/insert")
    public Result insertStudentInfo(@RequestBody Student student, @RequestParam String tableName) {
        try {
            studentService.insertStudentInfo(tableName, student);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("insert error");
        }
    }
}
