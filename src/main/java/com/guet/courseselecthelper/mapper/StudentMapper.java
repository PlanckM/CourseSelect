package com.guet.courseselecthelper.mapper;

import com.google.common.primitives.Bytes;
import com.guet.courseselecthelper.demos.entity.Student;
import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import org.apache.hadoop.hbase.client.Result;

public class StudentMapper implements RowMapper<Student> {
    private static final byte[] SINFO_FAMILY = "SInfo".getBytes();
    private static final byte[] STUDIES_FAMILY = "Studies".getBytes();
    private static final byte[] studentId = "studentId".getBytes();
    private static final byte[] sName = "sName".getBytes();
    private static final byte[] sGender = "sGender".getBytes();
    private static final byte[] sAge = "sAge".getBytes();
    private static final byte[] sDepartment = "sDepartment".getBytes();
    private static final byte[] sMajor = "sMajor".getBytes();

    @Override
    public Student mapRow(Result result, int i) throws Exception {

        Student.SInfo sInfo = new Student.SInfo(
                new String(result.getValue(SINFO_FAMILY, studentId)),
                new String(result.getValue(SINFO_FAMILY, sName)),
                new String(result.getValue(SINFO_FAMILY, sGender)),
                new String(result.getValue(SINFO_FAMILY, sAge))
        );

        Student.Studies studies;
        studies = new Student.Studies(
                new String(result.getValue(STUDIES_FAMILY, sDepartment)),
                new String(result.getValue(STUDIES_FAMILY, sMajor))
        );

        return new Student(sInfo, studies);
    }
}
