package com.lm.demo.entity;

import java.util.List;
public class Admin extends User {
    private List<User> managedUsers;
    private List<Course> managedCourses;
    private List<Notification> notifications;
}
