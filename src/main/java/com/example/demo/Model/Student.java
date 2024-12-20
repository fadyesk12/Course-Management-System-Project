package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student extends User {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            initialValue = 20220001,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Getter
    private Long id;
    @Getter
    private String name;
    @Getter
    private String email;
    @Getter
    private String password;
//    private List<Course> enrolledCourses;
//    private Map<String, Double> quizScores;
//    private Map<Assignment, String> assignmentsSubmitted;
//    private Map<Assignment, Double> assignmentGrades;
//    private List<Notification> notifications;

    public Student(Long id, String name, String email, String password)  {
        super(id, name, email, password);
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Student(String name, String email, String password)  {
        super(name, email, password);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
//                ", enrolledCourses=" + enrolledCourses +
//                ", quizScores=" + quizScores +
//                ", assignmentsSubmitted=" + assignmentsSubmitted +
//                ", assignmentGrades=" + assignmentGrades +
//                ", notifications=" + notifications +
                '}';
    }


}
