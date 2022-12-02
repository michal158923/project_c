package com.example.project_c.controller.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAndAnswersResponse {

    private String userCode;
    private String questionCount;
    private QuestionDto questionDto;
    private List<AnswerDto> answers;

}
