package com.guet.courseselecthelper.controller;

import com.guet.courseselecthelper.common.util.JwtUtils;
import com.guet.courseselecthelper.common.entity.Result;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/parseJwt")
public class ParseJwtController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @GetMapping
    public Result parseJwt(@RequestParam("jwt") String jwt) {
        Claims claims = JwtUtils.parseJWT(jwt);

        if (claims != null) {
            // 解析成功，返回JWT负载内容
            log.info("解析成功");
            return Result.success(claims);
        } else {
            // 解析失败
            log.info("解析失败");
            return Result.error("JWT-Parse-Failed");
        }
    }
    @GetMapping("/values")
    public List<String> getAllValues() {
        List<String> allValues = new ArrayList<>();

        Set<String> keys = stringRedisTemplate.keys("*");

        for (String key : keys) {
            String value = stringRedisTemplate.opsForValue().get(key);
            allValues.add(value);
        }

        return allValues;
    }
}
