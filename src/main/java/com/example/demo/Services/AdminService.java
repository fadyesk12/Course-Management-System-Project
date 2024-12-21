package com.example.demo.Services;

import com.example.demo.Model.Admin;
import com.example.demo.Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void registerAdmin(Admin admin) {
        Optional<Admin> existingAdmin = adminRepository.findByEmail(admin.getEmail());
        if (existingAdmin.isPresent()) {
            throw new IllegalStateException("Admin with this email already exists.");
        }
        adminRepository.save(admin);
    }

    public Admin login(String email, String password) {
        return adminRepository.findByEmail(email)
                .filter(admin -> admin.getPassword().equals(password))
                .orElseThrow(() -> new IllegalStateException("Invalid email or password."));
    }

    public List<Admin> getAllAdmins() {
        if (adminRepository.findAll().isEmpty()) {
            throw new IllegalStateException("No admins found.");
        }
        return adminRepository.findAll();
    }

    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new IllegalStateException("Admin not found.");
        }
        adminRepository.deleteById(id);
    }
}
