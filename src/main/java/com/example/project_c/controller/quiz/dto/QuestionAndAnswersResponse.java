package com.example.project_c.controller.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Builder
@AllArgsConstructor
public class QuestionAndAnswersResponse {

    private Integer questionId;
    private String questionContent;
    private List<AnswerDto> answers;

}
