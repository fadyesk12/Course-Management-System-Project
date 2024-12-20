package com.example.demo.Controllers;

import com.example.demo.Model.Admin;
import com.example.demo.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lms/admin")
public class AdminContoller {
    private final AdminService adminService;

    @Autowired
    public AdminContoller(AdminService adminService) {
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

    @PostMapping("/register")
    public void registerAdmin(@RequestBody Admin admin) {
        try {
            adminService.registerAdmin(admin);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
