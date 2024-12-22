package com.example.demo.Repositories;

import com.example.demo.Model.InstructorNotification;
import com.example.demo.Model.Lesson;
import com.example.demo.Model.StudentNotification;

// import com.example.demo.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface InstructorNotificationRepository extends JpaRepository<InstructorNotification,Long>{
    InstructorNotification findByid(Long id);
    List<InstructorNotification> findByInstructorId(Long courseId);
}