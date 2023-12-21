package com.guet.courseselecthelper.service;

public interface UserService {
    boolean authenticateUser(String account, String password, String role);
}
