package com.example.project_c.controller.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ScoreBoardDto {
    private String userName;
    private String scores;
}
