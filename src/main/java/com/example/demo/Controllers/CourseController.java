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

    @GetMapping("/getLessons")
    public List<Lesson> getLessons(@RequestBody Course course){
        return course.getLessons();
    }

    @GetMapping("/getEnrolled")
    public List<Student> getStudentList(@RequestBody Course course){
        return courseService.getStudentList(course);
    }

    @GetMapping("/getCourses")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }
}