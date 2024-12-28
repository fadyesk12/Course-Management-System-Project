package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Getter
@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates the ID
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

    @Column(nullable = false)
    private String text;

    @Setter
    @Column(name = "is_correct", nullable = false)
    private Boolean is_correct;

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", question=" + question +
                ", text='" + text + '\'' +
                ", is_correct=" + is_correct +
                '}';
    }

    @JsonProperty("question")
    public String getQuestionText() {
        return question != null ? question.getText() : null;
    }
}