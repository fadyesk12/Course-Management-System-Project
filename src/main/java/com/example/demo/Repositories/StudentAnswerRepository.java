package com.example.demo.Repositories;

import com.example.demo.Model.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {
    List<StudentAnswer> findByStudentId(Long studentId);
    List<StudentAnswer> findByQuestionId(Long questionId);
    List<StudentAnswer> findByAnswerId(Long answerId);
    List<StudentAnswer> findByStudentIdAndQuestionId(Long studentId, Long questionId);
    List<StudentAnswer> findByStudentIdAndAnswerId(Long studentId, Long answerId);
    List<StudentAnswer> findByQuestionIdAndAnswerId(Long questionId, Long answerId);
    List<StudentAnswer> findByStudentIdAndQuestionIdAndAnswerId(Long studentId, Long questionId, Long answerId);
}
