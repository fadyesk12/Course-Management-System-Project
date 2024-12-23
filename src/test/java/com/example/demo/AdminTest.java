package com.example.demo;
import com.example.demo.Controllers.AdminController;
import com.example.demo.Model.Admin;
import com.example.demo.Repositories.AdminRepository;
import com.example.demo.Services.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdminTest {
    @Autowired
    private AdminController adminController;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    @Test
    void testRegisterAdmin() {
        Admin admin = new Admin("abdalla", "abdalla@example.com", "password123");
        adminController.registerAdmin(admin);
        Optional<Admin> savedAdmin = adminRepository.findByEmail("abdalla@example.com");
        assertTrue(savedAdmin.isPresent());
        assertEquals("abdalla", savedAdmin.get().getName());
        assertEquals("abdalla@example.com", savedAdmin.get().getEmail());
    }

    @Test
    void testDeleteAdmin() {
        Admin admin = new Admin("fady", "fady@example.com", "password456");
        adminRepository.save(admin);
        adminController.deleteAdmin(admin.getId());
        Admin deletedAdmin = adminRepository.findById(admin.getId()).orElse(null);
        assertNull(deletedAdmin);
    }
}
