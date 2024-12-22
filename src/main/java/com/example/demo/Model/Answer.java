package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @Getter
    private Long id;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Getter
    @Column(nullable = false)
    private String text;

    @Getter
    @Column(nullable = false)
    private boolean is_correct;


}
