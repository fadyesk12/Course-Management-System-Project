package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "student_answers")
public class StudentAnswer {
    @Id
    @Getter
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Getter
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Getter
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Getter
    @Column(nullable = false)
    private String text;

    @Getter
    @Column(nullable = false)
    private boolean is_correct;

    public StudentAnswer() {
    }

    public StudentAnswer(Student student, Quiz quiz, Question question, String text, boolean is_correct) {
        this.student = student;
        this.quiz = quiz;
        this.question = question;
        this.text = text;
        this.is_correct = is_correct;
    }
}
