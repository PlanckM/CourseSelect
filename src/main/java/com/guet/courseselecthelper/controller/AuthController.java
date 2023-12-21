package com.guet.courseselecthelper.controller;

import com.guet.courseselecthelper.common.entity.Result;
import com.guet.courseselecthelper.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Resource
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping
    public ResponseEntity<Result> login(@RequestBody Map<String, String> loginRequest){
        String account = loginRequest.get("account");
        String password = loginRequest.get("password");
        String role = loginRequest.get("role");
        System.out.println(account);
        // 在UserService中验证用户凭证
        if (userService.authenticateUser(account, password, role)) {
            String token = stringRedisTemplate.opsForValue().get("JWT_" + account);
            return ResponseEntity.ok(Result.success(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Result.error("Invalid credentials"));
        }
    }
}
