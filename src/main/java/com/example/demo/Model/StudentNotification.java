package com.example.demo.Model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "Notification")
public class StudentNotification {
    @Id
    @Getter
    private Long id;
    @Getter
    @Setter
    private String message;
    @Getter
    private Date date;
    @Getter
    private boolean isRead = false;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
