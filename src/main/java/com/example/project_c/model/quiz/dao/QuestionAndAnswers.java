package com.example.project_c.model.quiz.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class QuestionAndAnswers {
    private Integer questionId;
    private String questionContent;
    private Integer AnswerId;
    private String AnswerContent;
    private Integer givenAnswerId;

    public QuestionDao getQuestionDao() {
        return QuestionDao.builder()
                .id(getQuestionId())
                .content(getQuestionContent())
                .givenAnswerId(getGivenAnswerId())
                .build();
    }

    public AnswerDao getAnswerDao() {
        return AnswerDao.builder()
                .id(getAnswerId())
                .content(getAnswerContent())
                .build();
    }

}
