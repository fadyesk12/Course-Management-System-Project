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



    public Lesson AddLesson(Long courseId, Lesson lesson){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId)); // making sure the course already exists
        if (lessonRepository.existsById(lesson.getId())){
            throw new IllegalStateException("Lesson already added");
        }
        lesson.setCourse(course);
        course.getLessons().add(lesson);
        courseRepository.save(course);

        return lesson;
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