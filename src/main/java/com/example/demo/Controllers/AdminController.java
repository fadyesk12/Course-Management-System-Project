package com.example.demo.Controllers;

import com.example.demo.Model.Admin;
import com.example.demo.Model.Instructor;
import com.example.demo.Model.Student;
import com.example.demo.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lms/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllAdmins() {
         try {
             return ResponseEntity.ok(adminService.getAllAdmins());
         } catch (Exception e) {
             return ResponseEntity.badRequest().body(null);
         }
    }

    @PostMapping("/admin-register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        try {
            Admin registeredAdmin = adminService.registerAdmin(admin);
            return ResponseEntity.status(HttpStatus.OK).body(registeredAdmin);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/admin-delete/{adminId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable("adminId") Long id) {
        try {
            adminService.deleteAdmin(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/instructor-register")
    public ResponseEntity<Instructor> registerInstructor(@RequestBody Instructor instructor) {
        try {
            Instructor registeredInstructor = adminService.registerInstructor(instructor);
            return ResponseEntity.status(HttpStatus.OK).body(registeredInstructor);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/instructor-delete/{instructorId}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable("instructorId") Long id) {
        try {
            adminService.deleteInstructor(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/student-register")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        try {
            Student registeredStudent = adminService.registerStudent(student);
            return ResponseEntity.status(HttpStatus.OK).body(registeredStudent);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/student-delete/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") Long id) {
        try {
            adminService.deleteStudent(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") Long id) {
        try {
            Student student = adminService.getStudent(id);
            return ResponseEntity.status(HttpStatus.OK).body(student);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<Instructor> getInstructor(@PathVariable("instructorId") Long id) {
        try {
            Instructor instructor = adminService.getInstructor(id);
            return ResponseEntity.status(HttpStatus.OK).body(instructor);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}