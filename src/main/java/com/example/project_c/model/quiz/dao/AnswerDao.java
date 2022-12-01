package com.example.project_c.model.quiz.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@AllArgsConstructor
public class AnswerDao {
    private Integer id;
    private String content;
}
