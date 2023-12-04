package com.guet.courseselecthelper.demos.service;


import com.guet.courseselecthelper.demos.entity.vo.Course;
import com.guet.courseselecthelper.demos.entity.vo.CourseInfoAndTeachingInfo;


import java.util.List;
import java.util.Map;

public interface CourseService {
    void saveCourseAndTeachingInfo(String courseId, Map<String, String> courseInfo, Map<String, String> teachingInfo);
    CourseInfoAndTeachingInfo getCourseAndTeachingInfo(String courseId);
    // 获取课程列表
    List<CourseInfoAndTeachingInfo> getAllCourses();
}
