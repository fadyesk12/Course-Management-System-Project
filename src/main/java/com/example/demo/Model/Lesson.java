package com.example.demo.Model;

import java.util.Date;
import java.util.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "Lesson")
public class Lesson {
    @Id
    private Long id;
    private String title;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable = false)
    private Long OTP;
    public Lesson(Long id, String title, Date date){
        this.id = id;
        this.title = title;
        this.date = date;
    }
    public Lesson(String title, Date date){
        this.title = title;
        this.date = date;
        OTP = (long) (Math.random() * 1000000);
    }

    @ManyToMany(mappedBy = "attendedLessons")
    private List<Student> students = new ArrayList<>();
}