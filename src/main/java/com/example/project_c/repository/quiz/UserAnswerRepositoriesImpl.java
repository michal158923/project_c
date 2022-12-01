package com.example.project_c.repository.quiz;

import com.example.project_c.model.home.MyUser;
import com.example.project_c.model.quiz.dao.QuestionAndAnswers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserAnswerRepositoriesImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<QuestionAndAnswers> findQuestionAndAnswersForUser(MyUser user) {
        return entityManager.createQuery(
                """
                    SELECT new com.example.project_c.model.quiz.dao.QuestionAndAnswers(
                            q.id, q.content, a.id, a.content, ua.givenAnswer.id)
                    FROM Answer a, UserAnswer ua
                    JOIN ua.lotQuestion q
                    WHERE ua.user = :user
                    AND q.id = ua.lotQuestion
                    AND q.id = a.question
                """
                , QuestionAndAnswers.class)//todo tu skończyłem
                .setParameter("user", user)
                .getResultList();
    }

}


