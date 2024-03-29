package com.guet.courseselecthelper.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseInfoAndTeachingInfo {
    private String courseId;
    private Map<Object, Object> courseInfo;
    private Map<Object, Object> teachingInfo;
}
