package com.example.demo.Services;

import com.example.demo.Model.Course;
import com.example.demo.Model.InstructorNotification;
import com.example.demo.Model.Student;
import com.example.demo.Model.StudentNotification;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.InstructorNotificationRepository;
import com.example.demo.Repositories.StudentNotificationRepository;
import com.example.demo.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EnrollmentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentNotificationRepository studentNotificationRepository;
    private final InstructorNotificationRepository instructorNotificationRepository;

    @Autowired
    public EnrollmentService(StudentRepository studentRepository, CourseRepository courseRepository,
                             StudentNotificationRepository studentNotificationRepository,
                             InstructorNotificationRepository instructorNotificationRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentNotificationRepository = studentNotificationRepository;
        this.instructorNotificationRepository = instructorNotificationRepository;
    }


    public Course enrollStudentInCourse(Long studentId, Long courseId) {
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

        StudentNotification notification = new StudentNotification();
        notification.setMessage("Added to course.");
        notification.setDate(new Date()); // Set date explicitly if not defaulted in the constructor
        notification.setStudent(student);
        studentNotificationRepository.save(notification);


        InstructorNotification Inotification = new InstructorNotification();
        Inotification.setMessage("Added Student to course.");
        Inotification.setInstructor(course.getInstructor());
        instructorNotificationRepository.save(Inotification);
        return course;
    }


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

        StudentNotification notification = new StudentNotification();
        notification.setMessage("Removed from course.");
        notification.setStudent(student);
        studentNotificationRepository.save(notification);


        InstructorNotification Inotification = new InstructorNotification();
        Inotification.setMessage("Removed student from course.");
        Inotification.setInstructor(course.getInstructor());
        instructorNotificationRepository.save(Inotification);
    }

    public Long getStudentAttendance(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student not found."));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException("Course not found."));
        if(!course.getEnrolledStudents().contains(student)){
            throw new IllegalStateException("Student is not enrolled in this course.");
        }
        return student.getAttendedLessons().stream().filter(lesson -> lesson.getCourse().getId().equals(courseId)).count();
    }
}