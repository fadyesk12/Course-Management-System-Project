package com.example.demo.Model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Course {
    private String id;
    private String title;
    private String description;
    private String duration;
    private Date creationDate;
    private Instructor instructor;
    private List<Student> enrolledStudents;
    private List<Lesson> lessons;
    private List<Quiz> quizzes;
    private List<Assignment> assignments;
    private Map<Lesson, String> lessonOTPs;
    private List<Notification> notifications;
}
