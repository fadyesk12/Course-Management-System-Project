package com.lm.demo.entity;

import java.util.List;
import java.util.Map;

public class Student extends User {
    private List<Course> enrolledCourses;
    private Map<String, Double> quizScores;
    private Map<Assignment, String> assignmentsSubmitted;
    private Map<Assignment, Double> assignmentGrades;
    private List<Notification> notifications;
}
