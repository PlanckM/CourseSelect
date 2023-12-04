package com.guet.courseselecthelper.demos.courseRepository;

import com.guet.courseselecthelper.demos.entity.vo.CourseInfoAndTeachingInfo;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Repository
public class CourseRepository {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void saveCourse(String courseId, Map<String, String> courseInfo) {
        String key = "CInfo:" + courseId;
        stringRedisTemplate.opsForHash().putAll(key, courseInfo);
        // 添加课程ID到集合中
        stringRedisTemplate.opsForSet().add("AllCourses", courseId);
    }

    public void saveTeaching(String courseId, Map<String, String> teachingInfo) {
        String key = "Teaching:" + courseId;
        stringRedisTemplate.opsForHash().putAll(key, teachingInfo);
    }

    public Map<Object, Object> getCourseInfo(String courseId) {
        String key = "CInfo:" + courseId;
        return stringRedisTemplate.opsForHash().entries(key);
    }

    public Map<Object, Object> getTeachingInfo(String courseId) {
        String key = "Teaching:" + courseId;
        return stringRedisTemplate.opsForHash().entries(key);
    }
    // 获取所有课程信息
    public List<CourseInfoAndTeachingInfo> getAllCourses() {
        Set<Object> allCourseIds = new HashSet<>(stringRedisTemplate.opsForSet().members("AllCourses"));
        List<CourseInfoAndTeachingInfo> allCourses = new ArrayList<>();

        for (Object courseId : allCourseIds) {
            String courseKey = "CInfo:" + courseId;
            String teachingKey = "Teaching:" + courseId;

            Map<Object, Object> courseInfo = stringRedisTemplate.opsForHash().entries(courseKey);
            Map<Object, Object> teachingInfo = stringRedisTemplate.opsForHash().entries(teachingKey);

            CourseInfoAndTeachingInfo result = new CourseInfoAndTeachingInfo();
            result.setCourseInfo(courseInfo);
            result.setTeachingInfo(teachingInfo);
            result.setCourseId((String) courseId);

            allCourses.add(result);
        }

        return allCourses;
    }

}
