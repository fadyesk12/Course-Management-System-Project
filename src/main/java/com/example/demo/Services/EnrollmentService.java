package com.example.demo.Services;

import com.example.demo.Model.Course;
import com.example.demo.Model.Student;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student not found."));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException("Course not found."));

        if (student.getEnrolledCourses().contains(course) || course.getEnrolledStudents().contains(student)) {
            throw new IllegalStateException("Student is already enrolled in this course.");
        }

        student.getEnrolledCourses().add(course);
        course.getEnrolledStudents().add(student);

        studentRepository.save(student);
    }

    @Transactional
    public void unenrollStudentFromCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student not found."));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException("Course not found."));

        if (!student.getEnrolledCourses().contains(course) || !course.getEnrolledStudents().contains(student)) {
            throw new IllegalStateException("Student is not enrolled in this course.");
        }

        student.getEnrolledCourses().remove(course);
        course.getEnrolledStudents().remove(student);

        studentRepository.save(student);
    }
}
