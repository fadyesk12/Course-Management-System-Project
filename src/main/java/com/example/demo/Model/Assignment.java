package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;


@Entity
@Getter
@Setter
@Table(name = "assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    private String description;

    @Column(name = "submission_deadline")
    private String submissionDeadline;

    @Column(name = "created_date", unique = true)
    private String createdDate;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    public Assignment(Long id, String title, String description, String submissionDate, String createdDate){
        this.id = id;
        this.title = title;
        this.description = description;
        this.submissionDeadline = submissionDate;
        this.createdDate = createdDate;
    }

    public Assignment() {

    }

    // @ManyToMany
    // @JoinColumn(name = "student_id")
    // private Student student;

    // @ManyToOne
    // @JoinColumn(name = "instructor_id")
    // private Instructor instructor;

    @JsonProperty("course")
    public Long getCourseId() {
        return course != null ? course.getId() : null;
    }
}
