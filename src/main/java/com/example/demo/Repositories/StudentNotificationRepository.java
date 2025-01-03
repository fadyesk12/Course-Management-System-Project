package com.example.demo.Repositories;

import com.example.demo.Model.Lesson;
import com.example.demo.Model.StudentNotification;

// import com.example.demo.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface StudentNotificationRepository extends JpaRepository<StudentNotification,Long>{
    StudentNotification findByid(Long id);
    Optional<List<StudentNotification>> findByStudentId(Long studentId);
}