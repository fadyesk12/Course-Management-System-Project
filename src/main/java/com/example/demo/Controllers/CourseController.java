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

// import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/lms/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping("/createCourse")
    public void createCourse(@RequestBody Course course){
        courseService.CreateCourse(course);
    }

    @PostMapping("/enroll/{course_ID}")
    public void AddStudent(@PathVariable("course_ID") String courseID, @RequestBody Student stud){
        courseService.AddStudent(courseID, stud);
    }

    @DeleteMapping("/unenroll/{course_ID}")
    public void RemoveStudent(@PathVariable("course_ID") String courseID, @RequestBody Student stud){
        courseService.RemoveStudent(courseID, stud);
    }

//    @PostMapping("/addLesson/{course_ID}")
//    public void AddLesson(@PathVariable("course_ID") String courseID, @RequestBody Lesson lesson){
//        courseService.AddLesson(courseID, lesson);
//    }

    @GetMapping("/getEnrolled")
    public List<Student> getStudentList(@RequestBody Course course){
        return courseService.getStudentList(course);
    }

    @GetMapping("/getCourses")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }
}