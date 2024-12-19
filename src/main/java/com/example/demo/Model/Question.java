package com.example.demo.Model;
import java.util.List;

public class Question {
    private String id;
    private String text;
    private String type;
    private List<String> options; // if it is mcq question
    private String correctAnswer;
}
