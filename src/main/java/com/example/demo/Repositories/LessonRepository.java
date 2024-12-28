package com.example.demo.Repositories;

import com.example.demo.Model.Lesson;
// import com.example.demo.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Optional<Lesson> findById(Long id);
    List<Lesson> findByCourse_Id(Long courseId); // Correct query method
    Optional<Lesson> findByOTP(Long OTP);
}
