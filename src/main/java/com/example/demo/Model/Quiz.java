package com.example.demo.Model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Quiz {
    private String id;
    private String title;
    private Date creationDate;
    private Course course;
    private List<Question> questions;
    private Map<Student, Double> scores;
}
