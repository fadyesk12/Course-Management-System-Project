package com.example.demo.Controllers;

import com.example.demo.Model.*;
import com.example.demo.Services.EnrollmentService;
import com.example.demo.Services.LessonService;
import com.example.demo.Services.StudentService;
import com.example.demo.Storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/lms/student")
public class StudentContoller {
    private final StudentService studentService;
    private final EnrollmentService enrollmentService;
    private final StorageService storageService;
    private final LessonService lessonService;

    @Autowired
    public StudentContoller(StudentService studentService, EnrollmentService enrollmentService, StorageService storageService, LessonService lessonService) {
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
        this.storageService = storageService;
        this.lessonService = lessonService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            return ResponseEntity.ok(studentService.getAllStudents());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("attendance/{studentId}/{courseId}/{lessonId}")
    public ResponseEntity<Lesson> attendLesson(@PathVariable("studentId") Long studentId, @PathVariable("lessonId") Long lessonId,
                             @RequestParam(name = "OTP", required = false) Long OTP, @PathVariable("courseId") Long courseId) {
        try {
            Lesson lesson = studentService.attendLesson(studentId, lessonId, OTP, courseId);
            return ResponseEntity.ok(lesson);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping("/enroll/{studentId}/{courseId}")
    public ResponseEntity<Course> enrollStudentInCourse( @PathVariable("studentId") Long studentId,@PathVariable("courseId") Long courseId) {
        try {
            Course course = enrollmentService.enrollStudentInCourse(studentId, courseId);
            return ResponseEntity.ok(course);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/RetrieveLessons/{studentId}/{courseId}")
    public ResponseEntity<Lesson> viewLessons(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId){
        try {
            List<Lesson> lessons = studentService.RetrieveLessons(studentId, courseId);
            return ResponseEntity.ok(lessons.get(0));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/RetrieveNotifications/{studentId}")
    public ResponseEntity<List<StudentNotification>> retrievStudentNotifications(@PathVariable("studentId") Long studentId){
        try {
            List<StudentNotification> notifications = studentService.retrieveNotifications(studentId);
            return ResponseEntity.ok(notifications);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

     @PostMapping("/submit-quizzes/{studentId}/{quizId}")
     public ResponseEntity<QuizSubmission> submitQuiz(@PathVariable Long quizId, @PathVariable Long studentId, @RequestBody List<StudentAnswer> answers) {
            try {
                QuizSubmission quizSubmission = studentService.submitQuiz(studentId, quizId, answers);
                return ResponseEntity.ok(quizSubmission);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
     }


     @PostMapping("/submit-assignment/{studentId}/{assignmentId}")
        public ResponseEntity<AssignmentSubmission> submitAssignment(@PathVariable Long studentId, @PathVariable Long assignmentId, @RequestParam("submissionFile") MultipartFile submissionFile) {
                try {
                    storageService.store(submissionFile);
                    AssignmentSubmission assignmentSubmission = studentService.submitAssignment(studentId, assignmentId, submissionFile.getOriginalFilename());
                    return ResponseEntity.ok(null);
                } catch (Exception e) {
                    return ResponseEntity.badRequest().body(null);
                }
        }
}
