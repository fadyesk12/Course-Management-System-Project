package com.example.demo.Model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "instructor_notification")
public class InstructorNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    private String message;
    @Getter
    private Date date = new Date();
    @Getter
    private boolean isRead;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonIgnore
    private Instructor instructor;

    public InstructorNotification(String message, Instructor instructor){
        this.instructor = instructor;
        this.message = message;
    }

    @JsonProperty("instructor")
    public String getInstructorName() {
        return instructor != null ? instructor.getName() : null;
    }
}