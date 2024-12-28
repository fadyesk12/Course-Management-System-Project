package com.example.demo.Controllers;


import com.example.demo.Model.Course;
import com.example.demo.Model.Lesson;
import com.example.demo.Model.Student;
import com.example.demo.Services.CourseService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/lms/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/getLessons/{courseId}")
    public List<Lesson> getLessons(@RequestParam("courseId") Long courseId){
        return courseService.getLessons(courseId);
    }

    @GetMapping("/getEnrolled/{courseId}")
    public List<Student> getStudentList(@RequestParam("courseId") Long courseId){
        return courseService.getStudentList(courseId);
    }

    @GetMapping("/getCourses")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }
}