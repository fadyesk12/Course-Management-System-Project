package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "quiz_submission")
public class QuizSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;


    @Column(name = "submission_date")
    private LocalDateTime submissionDate;

    @Column(name = "grade")
    private double grade;

    public QuizSubmission(Student student, Quiz quiz, Integer grade) {
        this.student = student;
        this.quiz = quiz;
        this.grade = grade;
        this.submissionDate = LocalDateTime.now();
    }
}
