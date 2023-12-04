package com.guet.courseselecthelper.demos.service.Impl;

import com.guet.courseselecthelper.demos.courseRepository.CourseRepository;
import com.guet.courseselecthelper.demos.entity.vo.Course;
import com.guet.courseselecthelper.demos.entity.vo.CourseInfoAndTeachingInfo;
import com.guet.courseselecthelper.demos.service.CourseService;
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

    public void saveCourseAndTeachingInfo(String courseId, Map<String, String> courseInfo, Map<String, String> teachingInfo) {
        courseRepository.saveCourse(courseId, courseInfo);
        courseRepository.saveTeaching(courseId, teachingInfo);
    }

    public CourseInfoAndTeachingInfo getCourseAndTeachingInfo(String courseId) {
        Map<Object, Object> courseInfo = courseRepository.getCourseInfo(courseId);
        Map<Object, Object> teachingInfo = courseRepository.getTeachingInfo(courseId);

        CourseInfoAndTeachingInfo result = new CourseInfoAndTeachingInfo();
        result.setCourseInfo(courseInfo);
        result.setTeachingInfo(teachingInfo);
        result.setCourseId(courseId);
        return result;
    }
    @Override
    public List<CourseInfoAndTeachingInfo> getAllCourses() {
        Set<Object> allCourseIds = new HashSet<>(stringRedisTemplate.opsForSet().members("AllCourses"));
        System.out.println(allCourseIds);
        List<CourseInfoAndTeachingInfo> allCourses = new ArrayList<>();

        for (Object courseId : allCourseIds) {
            CourseInfoAndTeachingInfo courseInfoAndTeachingInfo = getCourseAndTeachingInfo(courseId.toString());
            allCourses.add(courseInfoAndTeachingInfo);
        }

        return allCourses;
    }


}
