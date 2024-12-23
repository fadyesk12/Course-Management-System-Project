package com.example.demo.Services;

import com.example.demo.Model.Admin;
import com.example.demo.Model.Instructor;
import com.example.demo.Model.Student;
import com.example.demo.Repositories.AdminRepository;
import com.example.demo.Repositories.InstructorRepository;
import com.example.demo.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, InstructorRepository instructorRepository, StudentRepository studentRepository) {
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
        this.adminRepository = adminRepository;
    }

    public void registerAdmin(Admin admin) {
        Optional<Admin> existingAdmin = adminRepository.findByEmail(admin.getEmail());
        if (existingAdmin.isPresent()) {
            throw new IllegalStateException("Admin with this email already exists.");
        }
        adminRepository.save(admin);
    }
    public void registerInstructor(Instructor instructor) {
        Optional<Instructor> existingInstructor = instructorRepository.findByEmail(instructor.getEmail());
        if (existingInstructor.isPresent()) {
            throw new IllegalStateException("Instructor with this email already exists.");
        }
        instructorRepository.save(instructor);
    }
    public void registerStudent(Student student) {
        Optional<Student> existingStudent = studentRepository.findByEmail(student.getEmail());
        if (existingStudent.isPresent()) {
            throw new IllegalStateException("Student with this email already exists.");
        }
        studentRepository.save(student);
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
    public void deleteInstructor(Long id) {
        if (!instructorRepository.existsById(id)) {
            throw new IllegalStateException("Instructor not found.");
        }
        instructorRepository.deleteById(id);
    }
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalStateException("Student not found.");
        }
        studentRepository.deleteById(id);
    }
}
