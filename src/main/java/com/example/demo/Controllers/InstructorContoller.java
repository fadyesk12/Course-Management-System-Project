package com.example.demo.Controllers;

import com.example.demo.Model.Course;
import com.example.demo.Model.Instructor;
import com.example.demo.Model.InstructorNotification;
import com.example.demo.Model.Lesson;
import com.example.demo.Model.StudentNotification;
import com.example.demo.Services.EnrollmentService;
import com.example.demo.Services.InstructorCourseService;
import com.example.demo.Services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lms/instructor")
public class InstructorContoller {
    private final InstructorService instructorService;
    private final EnrollmentService enrollmentService;
    private final InstructorCourseService instructorCourseService;

    @Autowired
    public InstructorContoller(InstructorService instructorService, EnrollmentService enrollmentService, InstructorCourseService instructorCourseService) {
        this.instructorService = instructorService;
        this.enrollmentService = enrollmentService;
        this.instructorCourseService = instructorCourseService;
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

    // @PostMapping("/addLesson/{courseId}")
    // public void addLesson(@PathVariable("courseId") Long courseId, @RequestBody Lesson lesson){
    //     try {
    //         instructorCourseService.addLesson(courseId, lesson);
    //     }
    //     catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }
    // }

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
    public List<InstructorNotification> retrievStudentNotifications(@PathVariable("instructorId") Long instructorId){
        return instructorService.retrieveNotifications(instructorId);
    }
}
