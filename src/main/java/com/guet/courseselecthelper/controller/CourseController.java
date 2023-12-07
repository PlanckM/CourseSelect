package com.guet.courseselecthelper.controller;


import com.guet.courseselecthelper.common.entity.Result;

import com.guet.courseselecthelper.service.CourseService;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Resource
    private CourseService courseService;


    @PostMapping("/save")
    public Result saveCourseAndTeachingInfo(@RequestBody Map<String, Object> request) {
        String courseId = (String) request.get("courseId");
        System.out.println(courseId);
        Map<String, String> courseInfo = (Map<String, String>) request.get("courseInfo");
        Map<String, String> teachingInfo = (Map<String, String>) request.get("teachingInfo");

        courseService.saveCourseAndTeachingInfo(courseId, courseInfo, teachingInfo);
        return Result.success();
    }

    @GetMapping("/info")
    public Result getCourseAndTeachingInfo(@RequestParam String courseId) {
        return Result.success(courseService.getCourseAndTeachingInfo(courseId));
    }

    @GetMapping("/all")
    public Result getAllCourses() {
        return Result.success(courseService.getAllCourses());
    }

    @PostMapping("/selection")
    public Result insertCourseSelectionRecords(@RequestBody Map<String, Integer> selectCourseRequest){
        courseService.insertCourseSelectionRecords(selectCourseRequest.get("courseId"), selectCourseRequest.get("studentId"));
        return Result.success();
    }
}
