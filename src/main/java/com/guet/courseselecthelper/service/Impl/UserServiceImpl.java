package com.guet.courseselecthelper.service.Impl;

import com.guet.courseselecthelper.common.util.JwtUtils;
import com.guet.courseselecthelper.entity.User;
import com.guet.courseselecthelper.service.StudentService;
import com.guet.courseselecthelper.service.UserService;
import com.guet.courseselecthelper.mapper.UserMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Kevin
 * 结合hbase和mysql数据库实现登录校验
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private StudentService studentService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UserMapper userMapper;
    public User findByAccount(String account) {
        // 判断hbase中是否有这个账户
        if (studentService.findByAccount("student", account) != null){
            // 从mysql获取对应账户的密码
            String password = userMapper.findByAccount(account);
            return new User(account, password);
        } else{
            return null;
        }

    }
    @Override
    public boolean authenticateUser(String account, String password) {
        User user = findByAccount(account);
        if(user != null && user.getPassword() != null && user.getPassword().equals(password)){
            String token = JwtUtils.generateToken(user.getAccount());
            stringRedisTemplate.opsForValue().set("JWT_" + account, token, 6, TimeUnit.HOURS);
            return true;
        }
        else {
            return false;
        }
    }
}
