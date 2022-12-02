package com.example.project_c.controller.quiz.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QuestionDto {
    private Integer Id;
    private String content;
}
