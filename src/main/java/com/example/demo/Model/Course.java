package com.example.demo.Model;

import java.util.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {
    @Id
    @Getter
    private String id;
    @Getter
    private String title;
    @Getter
    private String description;
    @Getter
    private String duration;
    @Getter
    private Date creationDate;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
    @Getter
    @ManyToMany(mappedBy = "enrolledCourses")
    private Set<Student> enrolledStudents = new HashSet<>();
//    private List<Lesson> lessons;
//    private List<Quiz> quizzes;
//    private List<Assignment> assignments;
//    private Map<Lesson, String> lessonOTPs;
//    private List<Notification> notifications;

    public Course(String id, String title, String description, String duration, Date creationDate){
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.creationDate = creationDate;
    }

    public Course(String id, String title, String description){
        this.title = title;
        this.description = description;
    }

    public void AddStudent(Student s){
        enrolledStudents.add(s);
    }
    public void RemoveStudent(Student s){
        enrolledStudents.remove(s);
    }
//    public void AddLesson(Lesson lesson){
//        lessons.add(lesson);
//    }

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
