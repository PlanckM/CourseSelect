package com.guet.courseselecthelper.entity.vo;


import com.guet.courseselecthelper.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private StudentDto.sinfo sInfo;
    private StudentDto.studies studies;
    private String studentId;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class sinfo {
        private String studentId;
        private String sname;
        private String sgender;
        private String sage;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class studies {
        private String sdepartment;
        private String smajor;
    }
}
