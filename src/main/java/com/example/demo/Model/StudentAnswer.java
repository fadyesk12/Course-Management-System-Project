package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Table(name = "student_answers")
public class StudentAnswer {
    @Id
    @Getter
    private Long answerId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    @Getter
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonIgnore
    private Quiz quiz;


    @Getter
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "question_id")
    private Question question;

    @Getter
    @Column(nullable = false)
    private String text;

    @Getter
    @ManyToOne
    @JoinColumn(name = "quiz_submission_id")
    @JsonIgnore
    QuizSubmission quizSubmission;

    @JsonProperty("quiz")
    public String getQuizTitle() {
        try {
            return quiz.getTitle();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @JsonProperty("question")
    public String getQuestionText() {
        try {
            return question.getText();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @JsonProperty("student")
    public String getStudentName() {

        try {
            return student.getName();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @JsonProperty("answer")
    public String getAnswerText(){
        try {
            return text;
        } catch (NullPointerException e) {
            return null;
        }
    }

    StudentAnswer(Long id, String text){
        this.answerId = id;
        this.text = text;
    }
}
