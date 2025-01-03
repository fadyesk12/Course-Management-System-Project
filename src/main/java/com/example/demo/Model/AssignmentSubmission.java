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
@Table(name = "assignment_submission")
public class AssignmentSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "submitted_date")
    private LocalDateTime submitted_date;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    @JsonIgnore
    private Assignment assignment;

    private String submissionPath;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    @Column(name = "grade")
    private long grade;

    @Lob
    @Column(name = "file_content")
    private byte[] fileContent;

    @JsonProperty("assignment")
    public String getAssignmentTitle() {
        return assignment != null ? assignment.getTitle() : null;
    }

    @JsonProperty("student")
    public String getStudentName() {
        return student != null ? student.getName() : null;
    }
}
