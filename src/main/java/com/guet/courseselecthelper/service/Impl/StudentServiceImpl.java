package com.guet.courseselecthelper.service.Impl;

import com.guet.courseselecthelper.entity.Student;
import com.guet.courseselecthelper.entity.vo.StudentDto;
import com.guet.courseselecthelper.service.StudentService;
import com.guet.courseselecthelper.mapper.StudentIdMapper;
import com.guet.courseselecthelper.mapper.StudentMapper;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println(hbaseTemplate.get(tableName, rowKey, new StudentIdMapper()));
        return hbaseTemplate.get(tableName, rowKey, new StudentIdMapper());
    }

    @Override
    public List<StudentDto> getAllStudent() {
        Scan scan = new Scan();
        List<Student> students = hbaseTemplate.find("student", scan, new StudentMapper());

        // 转换为所需的格式
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            StudentDto studentDto = new StudentDto();
            studentDto.setStudentId(student.getSinfo().getStudentId());
            studentDto.setStudies(student.getStudies());
            studentDto.setSInfo(student.getSinfo());
            studentDtos.add(studentDto);
        }

        return studentDtos;
    }

    @Override
    public void insertStudentInfo(String tableName, Student student) {
        // 使用 StudentId 作为行键
        Put put = new Put(Bytes.toBytes(student.getSinfo().getStudentId()));

        // 列族 SInfo
        put.addColumn(Bytes.toBytes("SInfo"), Bytes.toBytes("StudentID"), Bytes.toBytes(student.getSinfo().getStudentId()));
        put.addColumn(Bytes.toBytes("SInfo"), Bytes.toBytes("SName"), Bytes.toBytes(student.getSinfo().getSname()));
        put.addColumn(Bytes.toBytes("SInfo"), Bytes.toBytes("SGender"), Bytes.toBytes(student.getSinfo().getSgender()));
        put.addColumn(Bytes.toBytes("SInfo"), Bytes.toBytes("SAge"), Bytes.toBytes(student.getSinfo().getSage()));

        // 列族 Studies
        put.addColumn(Bytes.toBytes("Studies"), Bytes.toBytes("SDepartment"), Bytes.toBytes(student.getStudies().getSdepartment()));
        put.addColumn(Bytes.toBytes("Studies"), Bytes.toBytes("SMajor"), Bytes.toBytes(student.getStudies().getSmajor()));

        hbaseTemplate.saveOrUpdate(tableName, put);
    }
}
