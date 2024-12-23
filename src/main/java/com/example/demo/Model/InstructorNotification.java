package com.example.demo.Model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "Notification")
public class InstructorNotification {
    @Id
    @Getter
    private Long id;
    @Getter
    @Setter
    private String message;
    @Getter
    private Date date;
    @Getter
    private boolean isRead;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
    public InstructorNotification(String message, Instructor instructor){
        this.message = message;
        this.instructor = instructor;
    }
}
