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
    private final LessonRepository lessonRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, LessonRepository lessonRepository) {
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
    }


    public List<Lesson> getLessons(Long courseId){
        return lessonRepository.findByCourse_Id(courseId);
    }

    public List<Student> getStudentList(Long courseId){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException("Course not found."));
        return course.getEnrolledStudents().stream().toList();
    }
    public List<Course> getAllCourses(){
        if (courseRepository.findAll().isEmpty()) {
            throw new IllegalStateException("No courses found.");
        }
        return courseRepository.findAll();
    }
}