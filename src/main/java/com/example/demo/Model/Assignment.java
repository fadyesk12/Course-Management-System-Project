package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;


@Entity
@Getter
@Setter
@Table(name = "assignment")
public class Assignment {
    @Id
    private Long id;
    private String title;
    private String description;
    private Date submissionDeadline;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
