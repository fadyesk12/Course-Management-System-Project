package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    private Student student;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;


    @Column(name = "submission_date")
    private LocalDateTime submissionDate;

    @Column(name = "grade")
    private double grade;

    @Setter
    @Getter
    @OneToMany(mappedBy = "quizSubmission")
    private List<StudentAnswer> answers;

    public QuizSubmission(Student student, Quiz quiz, Integer grade) {
        this.student = student;
        this.quiz = quiz;
        this.grade = grade;
        this.submissionDate = LocalDateTime.now();
    }

    @JsonProperty("student")
    public String getStudentName() {
        return student != null ? student.getName() : null;
    }

    @JsonProperty("quiz")
    public String getQuizTitle() {
        return quiz != null ? quiz.getTitle() : null;
    }
}
