package com.example.demo.Controllers;

import com.example.demo.Model.Admin;
import com.example.demo.Model.Instructor;
import com.example.demo.Model.Student;
import com.example.demo.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Admin> getAllAdmins() {
        try {
            return adminService.getAllAdmins();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping("/admin-register")
    public void registerAdmin(@RequestBody Admin admin) {
        try {
            adminService.registerAdmin(admin);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @DeleteMapping("/admin-delete/{adminId}")
    public void deleteAdmin(@PathVariable("adminId") Long id) {
        try {
            adminService.deleteAdmin(id);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @PostMapping("/instructor-register")
    public void registerInstructor(@RequestBody Instructor instructor) {
        try {
            adminService.registerInstructor(instructor);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @DeleteMapping("/instructor-delete/{instructorId}")
    public void deleteInstructor(@PathVariable("instructorId") Long id) {
        try {
            adminService.deleteInstructor(id);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @PostMapping("/student-register")
    public void registerStudent(@RequestBody Student student) {
        try {
            adminService.registerStudent(student);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @DeleteMapping("/student-delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        try {
            adminService.deleteStudent(id);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}