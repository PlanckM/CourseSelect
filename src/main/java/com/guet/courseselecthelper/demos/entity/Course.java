package com.guet.courseselecthelper.demos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String courseId;
    private Map<Object, Object> courseInfo;
    private Map<Object, Object> teachingInfo;
}
