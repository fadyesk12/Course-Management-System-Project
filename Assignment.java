package com.lm.demo.entity;

import java.util.Date;
import java.util.Map;

public class Assignment {
    private String id;
    private String title;
    private String description;
    private Date submissionDeadline;
    private Course course;
    private Map<Student, Double> grades;
}
