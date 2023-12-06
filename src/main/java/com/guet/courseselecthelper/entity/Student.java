package com.guet.courseselecthelper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private SInfo sInfo;
    private Studies studies;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SInfo {
        private String studentId;
        private String sName;
        private String sGender;
        private String sAge;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Studies {
        private String sDepartment;
        private String sMajor;
    }
}
