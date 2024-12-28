package com.example.demo.Model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "lesson") // Use lowercase for table name
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @Column(nullable = true)
    private Long OTP;

    @ManyToMany(mappedBy = "attendedLessons")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    public Lesson(Long id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public Lesson(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    @JsonProperty("students")
    public List<Long> getStudentIds() {
        try {
            List<Long> studentIds = new ArrayList<>();
            for (Student student : students) {
                studentIds.add(student.getId());
            }
            return studentIds;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @JsonProperty("courseId")
    public Long getCourseId() {
        return course != null ? course.getId() : null;
    }
}
