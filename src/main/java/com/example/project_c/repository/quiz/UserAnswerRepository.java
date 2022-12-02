package com.example.project_c.repository.quiz;

import com.example.project_c.model.home.MyUser;
import com.example.project_c.model.quiz.Answer;
import com.example.project_c.model.quiz.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Integer> {

    List<UserAnswer> findAllByUser(MyUser user);




//    public UserAnswer findFirstNullGivenAnswer(MyUser user);
}