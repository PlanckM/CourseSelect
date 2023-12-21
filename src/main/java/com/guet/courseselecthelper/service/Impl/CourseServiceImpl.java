package com.guet.courseselecthelper.service.Impl;

import com.guet.courseselecthelper.mapper.CourseMapper;
import com.guet.courseselecthelper.repository.CourseRepository;
import com.guet.courseselecthelper.entity.Course;
import com.guet.courseselecthelper.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private  CourseRepository courseRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private CourseMapper courseMapper;

    public void saveCourseAndTeachingInfo(String courseId, Map<String, String> courseInfo, Map<String, String> teachingInfo) {
        courseRepository.saveCourse(courseId, courseInfo);
        courseRepository.saveTeaching(courseId, teachingInfo);
    }

    public Course getCourseAndTeachingInfo(String courseId) {
        Map<Object, Object> courseInfo = courseRepository.getCourseInfo(courseId);
        Map<Object, Object> teachingInfo = courseRepository.getTeachingInfo(courseId);

        Course result = new Course();
        result.setCourseInfo(courseInfo);
        result.setTeachingInfo(teachingInfo);
        result.setCourseId(courseId);
        return result;
    }
    @Override
    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    // 选课
    @Override
    public void insertCourseSelectionRecords(Integer courseId, Integer studentId) {
        courseMapper.insertCourseSelectionRecords(courseId, studentId);
    }

    @Override
    public List<Course> SelectedCourses(Integer studentId) {
        // 获取学生选择的课程的ID
        List<String> coursesId = courseMapper.SelectedCourses(studentId);
        List<Course> allCourses = new ArrayList<>();
        // 遍历选择的课程的ID，来获取学生选择的课程信息
        for (String courseId : coursesId) {
            String courseKey = "CInfo:" + courseId;
            String teachingKey = "Teaching:" + courseId;

            Map<Object, Object> courseInfo = stringRedisTemplate.opsForHash().entries(courseKey);
            Map<Object, Object> teachingInfo = stringRedisTemplate.opsForHash().entries(teachingKey);

            Course result = new Course();
            result.setCourseInfo(courseInfo);
            result.setTeachingInfo(teachingInfo);
            result.setCourseId((String) courseId);

            allCourses.add(result);
        }
        return allCourses;
    }

    @Override
    public void deleteCourse(Integer courseId, Integer studentId) {
        courseMapper.deleteCourse(courseId, studentId);
    }

}
