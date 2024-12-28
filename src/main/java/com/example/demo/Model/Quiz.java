package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @Column(name = "created_date", unique = true)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private int duration;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonIgnore
    private Instructor instructor;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Question> questions;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_quiz",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "quiz_id")
    )
    @JsonIgnore
    private Set<Student> students;

    public Quiz(String title, Course course, int duration, Instructor instructor) {
        this.title = title;
        this.course = course;
        this.duration = duration;
        this.instructor = instructor;
        this.createdDate = LocalDateTime.now();
    }

    @JsonProperty("course")
    public String getCourseTitle() {
        return course != null ? course.getTitle() : null;
    }

    @JsonProperty("instructor")
    public String getInstructorName() {
        return instructor != null ? instructor.getName() : null;
    }

    @JsonProperty("questions")
    public List<String> getQuestionTexts() {
        try {
            List<String> questionTexts = new ArrayList<>();
            for (Question question : questions) {
                questionTexts.add(question.getText());
            }
            return questionTexts;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @JsonProperty("students")
    public List<String> getStudentNames() {
        try {
            List<String> studentNames = new ArrayList<>();
            for (Student student : students) {
                studentNames.add(student.getName());
            }
            return studentNames;
        } catch (NullPointerException e) {
            return null;
        }
    }
}