package com.lm.demo.entity;

import java.util.List;
import java.util.Map;

public class Instructor extends User {
    private String specialization;
    private List<Course> createdCourses;
    private Map<Course, List<Question>> questionBanks;
    private List<Notification> notifications;
}
