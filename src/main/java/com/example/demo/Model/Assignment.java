package com.example.demo.Model;

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
    private Date submissionDeadline;

    @Column(name = "created_date", unique = true)
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // @ManyToMany
    // @JoinColumn(name = "student_id")
    // private Student student;

    // @ManyToOne
    // @JoinColumn(name = "instructor_id")
    // private Instructor instructor;
}
