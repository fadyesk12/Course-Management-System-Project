package com.example.demo.Controllers;

import com.example.demo.Model.*;
import com.example.demo.Services.EnrollmentService;
import com.example.demo.Services.LessonService;
import com.example.demo.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/lms/student")
public class StudentContoller {
    private final StudentService studentService;
    private final EnrollmentService enrollmentService;

    private final LessonService lessonService;

    @Autowired
    public StudentContoller(StudentService studentService, EnrollmentService enrollmentService, LessonService lessonService) {
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
        this.lessonService = lessonService;
    }


    @GetMapping("/all")
    public List<Student> getAllStudents() {
        try {
            return studentService.getAllStudents();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping("attendance/{studentId}/{courseId}/{lessonId}")
    public void attendLesson(@PathVariable("studentId") Long studentId, @PathVariable("lessonId") Long lessonId,
                             @RequestParam(value = "OTP", required = false) Long OTP, @PathVariable("courseId") Long courseId) {
        try {
            studentService.attendLesson(studentId, lessonId, OTP, courseId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @PostMapping("/enroll/{studentId}/{courseId}")
    public void enrollStudentInCourse( @PathVariable("studentId") Long studentId,@PathVariable("courseId") Long courseId) {
        try {
            enrollmentService.enrollStudentInCourse(studentId, courseId);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/RetrieveLessons/{courseId}")
    public List<Lesson> viewLessons(@PathVariable("courseId") Long courseId){
        return studentService.RetrieveLessons(courseId);
    }

    @GetMapping("/RetrieveNotifications/{studentId}")
    public List<StudentNotification> retrievStudentNotifications(@PathVariable("studentId") Long studentId){
        return studentService.retrieveNotifications(studentId);
    }

     @PostMapping("/submit-quizzes/{studentId}/{quizId}")
     public void submitQuiz(@PathVariable Long quizId, @PathVariable Long studentId, @RequestBody List<StudentAnswer> answers) {
            studentService.submitQuiz(quizId, studentId, answers);

         return;
     }

}
