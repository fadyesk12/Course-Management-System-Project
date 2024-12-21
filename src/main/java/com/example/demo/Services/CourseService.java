package com.example.demo.Services;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Course;
import com.example.demo.Model.Student;
import com.example.demo.Model.Lesson;
import com.example.demo.Repositories.CourseRepository;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }



//    public void AddLesson(String courseID, Lesson lesson){
//        Course course = courseRepository.findByID(courseID);
//        if (course == null) {
//            throw new IllegalStateException("Course with this ID doesn't exist.");
//        }
//        course.AddLesson(lesson);
//        courseRepository.save(course);
//    }
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