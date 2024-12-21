package com.example.demo.Services;

import com.example.demo.Model.Instructor;
import com.example.demo.Repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public void registerInstructor(Instructor instructor) {
        Optional<Instructor> existingInstructor = instructorRepository.findByEmail(instructor.getEmail());
        if (existingInstructor.isPresent()) {
            throw new IllegalStateException("Instructor with this email already exists.");
        }
        instructorRepository.save(instructor);
    }

    public Instructor login(String email, String password) {
        return instructorRepository.findByEmail(email)
                .filter(instructor -> instructor.getPassword().equals(password))
                .orElseThrow(() -> new IllegalStateException("Invalid email or password."));
    }

    public List<Instructor> getAllInstructors() {
        if (instructorRepository.findAll().isEmpty()) {
            throw new IllegalStateException("No instructors found.");
        }
        return instructorRepository.findAll();
    }

    public void deleteInstructor(Long id) {
        if (!instructorRepository.existsById(id)) {
            throw new IllegalStateException("Instructor not found.");
        }
        instructorRepository.deleteById(id);
    }
}
