package com.example.project_c.model.quiz.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Builder
@Getter
@ToString
@AllArgsConstructor
public class QuestionDao {

    private Integer id;
    private String content;
    private Integer givenAnswerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDao that = (QuestionDao) o;
        return Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
