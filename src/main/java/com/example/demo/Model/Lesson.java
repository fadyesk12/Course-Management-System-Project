package com.example.demo.Model;

import java.util.Date;
import java.util.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "Lesson")
public class Lesson {
    @Id
    @Getter
    private Long id;
    private String title;
    private Date date;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
