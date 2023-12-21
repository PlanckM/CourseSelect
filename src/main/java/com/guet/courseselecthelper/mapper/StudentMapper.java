package com.guet.courseselecthelper.mapper;

import com.guet.courseselecthelper.entity.Student;
import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import org.apache.hadoop.hbase.client.Result;

public class StudentMapper implements RowMapper<Student> {
    private static final byte[] SINFO_FAMILY = "SInfo".getBytes();
    private static final byte[] STUDIES_FAMILY = "Studies".getBytes();
    private static final byte[] StudentId = "StudentID".getBytes();
    private static final byte[] SName = "SName".getBytes();
    private static final byte[] SGender = "SGender".getBytes();
    private static final byte[] SAge = "SAge".getBytes();
    private static final byte[] SDepartment = "SDepartment".getBytes();
    private static final byte[] SMajor = "SMajor".getBytes();

    @Override
    public Student mapRow(Result result, int i) throws Exception {

        Student.sinfo sInfo = new Student.sinfo(
                new String(result.getValue(SINFO_FAMILY, StudentId)),
                new String(result.getValue(SINFO_FAMILY, SName)),
                new String(result.getValue(SINFO_FAMILY, SGender)),
                new String(result.getValue(SINFO_FAMILY, SAge))
        );

        Student.studies studies;
        studies = new Student.studies(
                new String(result.getValue(STUDIES_FAMILY, SDepartment)),
                new String(result.getValue(STUDIES_FAMILY, SMajor))
        );

        return new Student(sInfo, studies);
    }
}
