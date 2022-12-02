package com.example.project_c.model.quiz;

import lombok.Getter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
@Getter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private boolean correctFlag;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;
}
