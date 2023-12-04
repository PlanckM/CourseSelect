package com.guet.courseselecthelper.demos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Student {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SInfo {
        private String studentID;
        private String sName;
        private String sGender;
        private Integer sAge;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Studies {
        private String sDepartment;
        private String smajor;
    }
}
