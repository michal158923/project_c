package com.example.project_c.controller.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GivenAnswerRequest {
    private Integer questionId;
    private Integer givenAnswerId;
}
