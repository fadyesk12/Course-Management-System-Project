package com.example.demo.Model;

import java.util.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {
    @Id
    private Long id;
    private String title;
    private String description;
    private String duration;
    private Date creationDate;
    @Setter
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
    @ManyToMany(mappedBy = "enrolledCourses")
    private Set<Student> enrolledStudents = new HashSet<>();
    @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Lesson> lessons;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Quiz> quizzes;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Assignment> assignments;
//    private Map<Lesson, String> lessonOTPs;
//    private List<Notification> notifications;

    public Course(Long id, String title, String description, String duration, Date creationDate){
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.creationDate = creationDate;
    }

    public Course(Long id, String title, String description){
        this.title = title;
        this.description = description;
    }

    public Course(Long id, String title, String description, String duration, Date creationDate, Instructor instructor){
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.creationDate = creationDate;
        this.instructor = instructor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
