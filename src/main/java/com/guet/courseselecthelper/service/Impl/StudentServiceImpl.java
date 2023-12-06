package com.guet.courseselecthelper.service.Impl;

import com.guet.courseselecthelper.entity.Student;
import com.guet.courseselecthelper.service.StudentService;
import com.guet.courseselecthelper.mapper.StudentIdMapper;
import com.guet.courseselecthelper.mapper.StudentMapper;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private HbaseTemplate hbaseTemplate;
    @Override
    public Student findById(String tableName, String account) {
        return hbaseTemplate.get(tableName, account, new StudentMapper());
    }
    // 查询hbase中的指定账户
    @Override
    public String findByAccount(String tableName, String rowKey){
        return hbaseTemplate.get(tableName, rowKey, new StudentIdMapper());
    }

    @Override
    public void insertStudentInfo() {

    }
}
