package com.example.demo.Services;

import java.util.List;

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

    public Boolean CreateCourse(Course course){
        return true;
    }
    public Boolean AddStudent(Student stud){
        return true;
    }
    public Boolean RemoveStudent(Student stud){
        return true;
    }
    public Boolean AddLesson(Lesson lesson){
        return true;
    }
    public List<Student> getStudentList(){
        return null;
    }
    public List<Course> getAllCourses(){
        return null;
    }
}