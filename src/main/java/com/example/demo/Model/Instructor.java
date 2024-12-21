package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Entity
@Table(name = "instructor")

public class Instructor extends User {
    @Id
    @SequenceGenerator(
            name = "instructor_sequence",
            sequenceName = "instructor_sequence",
            initialValue = 77770001,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "instructor_sequence"
    )
    @Getter
    private Long id;
    @Getter
    private String name;
    @Getter
    private String email;
    @Getter
    private String password;
    @Getter
    private String specialization;
//    private List<Course> createdCourses;
//    private Map<Course, List<Question>> questionBanks;
//    private List<Notification> notifications;

    public Instructor(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getRole() {
        return "Instructor";
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", specialization='" + specialization + '\'' +
//                ", createdCourses=" + createdCourses + '\'' +
//                ", questionBanks=" + questionBanks + '\'' +
//                ", notifications=" + notifications + '\'' +
                '}';
    }
}
