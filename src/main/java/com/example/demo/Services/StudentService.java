package com.example.demo.Services;

// import com.example.demo.Model.Course;
import com.example.demo.Model.Course;
import com.example.demo.Model.Lesson;
import com.example.demo.Model.Student;
import com.example.demo.Model.StudentNotification;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.LessonRepository;
import com.example.demo.Repositories.StudentNotificationRepository;
import com.example.demo.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final StudentNotificationRepository studentNotificationRepository;

    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, LessonRepository lessonRepository, StudentNotificationRepository studentNotificationRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
        this.studentNotificationRepository = studentNotificationRepository;
        this.courseRepository = courseRepository;
    }




    public Student login(String email, String password) {
        return studentRepository.findByEmail(email)
                .filter(student -> student.getPassword().equals(password))
                .orElseThrow(() -> new IllegalStateException("Invalid email or password."));
    }

    public List<Student> getAllStudents() {
        if (studentRepository.findAll().isEmpty()) {
            throw new IllegalStateException("No students found.");
        }
        return studentRepository.findAll();
    }



    public List<Lesson> RetrieveLessons(Long courseId){
        return lessonRepository.findByCourseId(courseId);
    }

    public List<StudentNotification> retrieveNotifications(Long studentId){
        return studentNotificationRepository.findByStudentId(studentId);
    }

    public void attendLesson(Long studentId, Long lessonId, Long otp, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student not found."));
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalStateException("Lesson not found."));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException("Course not found."));
        if(!student.getAttendedLessons().contains(lesson)){
            if (lesson.getOTP() != otp) {
                throw new IllegalStateException("Invalid OTP.");
            }
        }
        if (!lesson.getCourse().getId().equals(courseId)) {
            throw new IllegalStateException("Lesson not found in course.");
        }
        student.getAttendedLessons().add(lesson);
        studentRepository.save(student);
    }

}
