package com.example.demo.Services;

import java.util.List;
// import java.util.Optional;

import com.example.demo.Model.*;
import com.example.demo.Repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositories.CourseRepository;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, LessonRepository lessonRepository) {
        this.courseRepository = courseRepository;
    }




    public List<Student> getStudentList(Course course){
        return course.getEnrolledStudents().stream().toList();
    }
    public List<Course> getAllCourses(){
        if (courseRepository.findAll().isEmpty()) {
            throw new IllegalStateException("No courses found.");
        }
        return courseRepository.findAll();
    }
}