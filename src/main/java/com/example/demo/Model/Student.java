package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.*;

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
    @Column(name = "student_id")
    private Long id;
    @Getter
    @Column(name = "student_name")
    private String name;
    @Getter
    @Column(name = "student_email")
    private String email;
    @Getter
    @Column(name = "student_password")
    private String password;

    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_enrolled_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> enrolledCourses = new HashSet<>();
//    private Map<String, Double> quizScores;
//    private Map<Assignment, String> assignmentsSubmitted;
//    private Map<Assignment, Double> assignmentGrades;

//    @Getter
//    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Notification> notifications;

    public Student(Long id, String name, String email, String password)  {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Student(String name, String email, String password)  {
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToMany(mappedBy = "students",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Quiz> quizzes;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentAnswer> studentAnswers;
}
