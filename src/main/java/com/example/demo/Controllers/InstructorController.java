package com.example.demo.Controllers;

import com.example.demo.Model.Course;
import com.example.demo.Model.Instructor;
import com.example.demo.Model.InstructorNotification;
import com.example.demo.Model.Lesson;
import com.example.demo.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lms/instructor")
public class InstructorController {
    private final InstructorService instructorService;
    private final EnrollmentService enrollmentService;
    private final InstructorCourseService instructorCourseService;

    private final LessonService lessonService;


    @Autowired
    public InstructorController(InstructorService instructorService, EnrollmentService enrollmentService, InstructorCourseService instructorCourseService, LessonService lessonService) {
        this.instructorService = instructorService;
        this.enrollmentService = enrollmentService;
        this.instructorCourseService = instructorCourseService;
        this.lessonService = lessonService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        try {
            List<Instructor> instructors = instructorService.getAllInstructors();
            return ResponseEntity.ok(instructors);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/createCourse/{instructorId}")
    public ResponseEntity<Course> createCourse(@PathVariable("instructorId") Long instructorId, @RequestBody Course course) {
        try {
            Course created = instructorCourseService.createCourse(instructorId, course);
            return ResponseEntity.ok(created);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PostMapping("/addLesson/{course_ID}")
    public ResponseEntity<Lesson> AddLesson(@PathVariable("course_ID") Long courseID, @RequestBody Lesson lesson){
        try {
            Lesson created = lessonService.AddLesson(courseID, lesson);
            return ResponseEntity.ok(created);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @DeleteMapping("/deleteLesson/{course_ID}/{lesson_ID}")
    public ResponseEntity<Void> deleteLesson(@PathVariable("course_ID") Long courseID, @PathVariable("lesson_ID") Long lessonID){
        try {
            lessonService.deleteLesson(courseID, lessonID);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/editLesson/{course_ID}/{lesson_ID}")
    public ResponseEntity<Lesson> editLesson(@PathVariable("course_ID") Long courseID, @PathVariable("lesson_ID") Long lessonID, @RequestBody Lesson lesson){
        try {
            Lesson edited = lessonService.editLesson(courseID, lessonID, lesson);
            return ResponseEntity.ok(edited);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("unenrollStudent/{studentId}/{courseId}")
    public ResponseEntity<Void> unenrollStudent(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId) {
        try {
            enrollmentService.unenrollStudentFromCourse(studentId, courseId);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }




    @GetMapping("/RetrieveNotifications/{instructorId}")
    public ResponseEntity<List<InstructorNotification>> retrieveInstructorNotifications(@PathVariable("instructorId") Long instructorId){
        try {
            List<InstructorNotification> notifications = instructorService.retrieveNotifications(instructorId);
            return ResponseEntity.ok(notifications);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/generateOTP/{lessonId}")
    public ResponseEntity<Lesson> generateOTP(@PathVariable("lessonId") Long lessonId){
        try {
            Lesson lesson = lessonService.generateOTP(lessonId);
            return ResponseEntity.ok(lesson);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}