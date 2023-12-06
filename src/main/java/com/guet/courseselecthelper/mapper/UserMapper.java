package com.guet.courseselecthelper.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    String findByAccount(String account);
}
