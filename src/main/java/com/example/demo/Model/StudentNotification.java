package com.example.demo.Model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_notification")
@NoArgsConstructor
@Getter
@Setter
public class StudentNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private Date date = new Date(); // Default to current date

    private boolean isRead = false;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

}