package com.example.demo.Controllers;

import com.example.demo.Model.Student;
import com.example.demo.Services.EnrollmentService;
import com.example.demo.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lms/student")
public class StudentContoller {
    private final StudentService studentService;
    private final EnrollmentService enrollmentService;

    @Autowired
    public StudentContoller(StudentService studentService, EnrollmentService enrollmentService) {
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
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

    @PostMapping("/register")
    public void registerStudent(@RequestBody Student student) {
        try {
            studentService.registerStudent(student);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        try {
            studentService.deleteStudent(id);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @PutMapping("/enroll/{studentId}/{courseId}")
    public void enrollStudentInCourse( @PathVariable("studentId") Long studentId,@PathVariable("courseId") String courseId) {
        try {
            enrollmentService.enrollStudentInCourse(studentId, courseId);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
