package com.guet.courseselecthelper.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseMapper {
    void insertCourseSelectionRecords(@Param("courseId") Integer courseId,@Param("studentId") Integer studentId);
}
