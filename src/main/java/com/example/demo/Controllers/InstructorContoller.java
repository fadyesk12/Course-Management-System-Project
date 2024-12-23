package com.example.demo.Controllers;

import com.example.demo.Model.Course;
import com.example.demo.Model.Instructor;
import com.example.demo.Model.InstructorNotification;
import com.example.demo.Model.Lesson;
import com.example.demo.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lms/instructor")
public class InstructorContoller {
    private final InstructorService instructorService;
    private final EnrollmentService enrollmentService;
    private final InstructorCourseService instructorCourseService;

    private final LessonService lessonService;


    @Autowired
    public InstructorContoller(InstructorService instructorService, EnrollmentService enrollmentService, InstructorCourseService instructorCourseService, LessonService lessonService) {
        this.instructorService = instructorService;
        this.enrollmentService = enrollmentService;
        this.instructorCourseService = instructorCourseService;
        this.lessonService = lessonService;
    }


    @GetMapping("/all")
    public List<Instructor> getAllInstructors() {
        try {
            return instructorService.getAllInstructors();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping("/createCourse/{instructorId}")
    public void createCourse(@PathVariable("instructorId") Long instructorId, @RequestBody Course course) {
        try {
            instructorCourseService.createCourse(instructorId, course);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @PostMapping("/addLesson/{course_ID}")
    public void AddLesson(@PathVariable("course_ID") Long courseID, @RequestBody Lesson lesson){
        lessonService.AddLesson(courseID, lesson);
    }
    @DeleteMapping("/deleteLesson/{course_ID}/{lesson_ID}")
    public void deleteLesson(@PathVariable("course_ID") Long courseID, @PathVariable("lesson_ID") Long lessonID){
        lessonService.deleteLesson(courseID, lessonID);
    }
    @PostMapping("/editLesson/{course_ID}/{lesson_ID}")
    public void editLesson(@PathVariable("course_ID") Long courseID, @PathVariable("lesson_ID") Long lessonID, @RequestBody Lesson lesson){
        lessonService.editLesson(courseID, lessonID, lesson);
    }

    @DeleteMapping("unentollStudent/{studentId}/{courseId}")
    public void unentollStudent(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId) {
        try {
            enrollmentService.unenrollStudentFromCourse(studentId, courseId);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @GetMapping("/RetrieveNotifications/{instructorId}")
    public List<InstructorNotification> retrieveInstructorNotifications(@PathVariable("instructorId") Long instructorId){
        return instructorService.retrieveNotifications(instructorId);
    }
}