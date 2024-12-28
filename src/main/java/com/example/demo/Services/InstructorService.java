package com.example.demo.Services;

import com.example.demo.Model.Course;
import com.example.demo.Model.Instructor;
import com.example.demo.Model.InstructorNotification;
import com.example.demo.Model.Student;
import com.example.demo.Model.StudentNotification;
import com.example.demo.Model.Lesson;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.InstructorNotificationRepository;
import com.example.demo.Repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final InstructorNotificationRepository instructorNotificationRepository;
    @Autowired
    public InstructorService(InstructorRepository instructorRepository, InstructorNotificationRepository instructorNotificationRepository) {
        this.instructorRepository = instructorRepository;
        this.instructorNotificationRepository = instructorNotificationRepository;
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



    public List<InstructorNotification> retrieveNotifications(Long instructorId){
        return instructorNotificationRepository.findByInstructorId(instructorId);
    }

}