package com.guet.courseselecthelper.demos.controller;

import com.guet.courseselecthelper.common.entity.Result;
import com.guet.courseselecthelper.demos.entity.Student;
import com.guet.courseselecthelper.demos.service.StudentService;
import com.guet.courseselecthelper.demos.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;
    @Resource
    private UserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("{tableName}/{rowKey}")
    public Student getStudentById(@PathVariable String tableName, @PathVariable String rowKey){
        return studentService.findById(tableName, rowKey);
    }


    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestBody Map<String, String> loginRequest){
        String account = loginRequest.get("account");
        String password = loginRequest.get("password");
        // 在UserService中验证用户凭证
        if (userService.authenticateUser(account, password)) {
            String token = stringRedisTemplate.opsForValue().get("JWT_" + account);
            return ResponseEntity.ok(Result.success(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Result.error("Invalid credentials"));
        }
    }
}
