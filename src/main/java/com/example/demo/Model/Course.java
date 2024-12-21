package com.example.demo.Model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {
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
    private Instructor instructor;
    @Getter
    private List<Student> enrolledStudents;
    private List<Lesson> lessons;
    private List<Quiz> quizzes;
    private List<Assignment> assignments;
    private Map<Lesson, String> lessonOTPs;
    private List<Notification> notifications;

    public Course(String id, String title, String description, String duration, Date creationDate){
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.creationDate = creationDate;
    }

    public void AddStudent(Student s){
        enrolledStudents.add(s);
    }
    public void RemoveStudent(Student s){
        enrolledStudents.remove(s);
    }
    public void AddLesson(Lesson lesson){
        lessons.add(lesson);
    }
    
}
