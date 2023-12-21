package com.guet.courseselecthelper.service;


import com.guet.courseselecthelper.entity.Course;


import java.util.List;
import java.util.Map;

public interface CourseService {
    void saveCourseAndTeachingInfo(String courseId, Map<String, String> courseInfo, Map<String, String> teachingInfo);
    Course getCourseAndTeachingInfo(String courseId);
    // 获取课程列表
    List<Course> getAllCourses();

    void insertCourseSelectionRecords(Integer courseId, Integer studentId);

    List<Course> SelectedCourses(Integer studentId);

    void deleteCourse(Integer courseId, Integer studentId);
}
