package com.example.demo.Services;

import com.example.demo.Model.Course;
import com.example.demo.Model.Instructor;
import com.example.demo.Model.Lesson;
import com.example.demo.Model.StudentNotification;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.InstructorRepository;
import com.example.demo.Repositories.LessonRepository;
import com.example.demo.Repositories.StudentNotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstructorCourseService {
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public InstructorCourseService(InstructorRepository instructorRepository, CourseRepository courseRepository, LessonRepository lessonRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void createCourse(Long instructorId, Course course) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IllegalStateException("Instructor not found."));
        if (instructor.getCreatedCourses().contains(course)) {
            throw new IllegalStateException("Instructor already created this course.");
        }
        if (courseRepository.existsById(course.getId())) {
            throw new IllegalStateException("Course already exists.");
        }
        course.setInstructor(instructor);
        instructor.getCreatedCourses().add(course);
        instructorRepository.save(instructor);
    }

    

}
