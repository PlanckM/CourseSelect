package com.guet.courseselecthelper.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {
    void insertCourseSelectionRecords(@Param("courseId") Integer courseId,@Param("studentId") Integer studentId);

    List<String> SelectedCourses(Integer studentId);

    void deleteCourse(@Param("courseId") Integer courseId, @Param("studentId") Integer studentId);
}
