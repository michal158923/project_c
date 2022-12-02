package com.example.project_c.repository.quiz;

import com.example.project_c.model.home.MyUser;
import com.example.project_c.model.quiz.UserAnswer;
import com.example.project_c.model.quiz.dao.QuestionAndAnswers;
import com.example.project_c.model.quiz.dao.ScoreBoardDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class QuizRepositoriesImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<QuestionAndAnswers> findQuestionAndAnswersForUser(MyUser user) {
        return entityManager.createQuery("""
                            SELECT new com.example.project_c.model.quiz.dao.QuestionAndAnswers(
                                    q.id, q.content, a.id, a.content, ua.givenAnswer.id)
                            FROM Answer a, UserAnswer ua
                            JOIN ua.lotQuestion q
                            WHERE ua.user = :user
                            AND q.id = ua.lotQuestion
                            AND q.id = a.question
                        """, QuestionAndAnswers.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Transactional
    public void updateUserAnswer(Long userId, Integer questionId, Integer givenAnswerId, boolean correctFlag) {
        entityManager.createQuery("""
                        UPDATE UserAnswer ua SET
                                ua.givenAnswer.id = :givenAnswerIdParam,
                                ua.correctChoiceFlag = :correctFlagParam
                        WHERE ua.user.id = :userIdParam
                        AND ua.lotQuestion.id = :questionIdParam
                         """)
                .setParameter("userIdParam", userId)
                .setParameter("questionIdParam", questionId)
                .setParameter("givenAnswerIdParam", givenAnswerId)
                .setParameter("correctFlagParam", correctFlag)
                .executeUpdate();
    }

    public List<ScoreBoardDao> userAnswerJoinUser() {
        return entityManager.createQuery("""
                            SELECT new com.example.project_c.model.quiz.dao.ScoreBoardDao(
                                    u.name, ua.correctChoiceFlag)
                            FROM UserAnswer ua
                            JOIN ua.user u
                        """, ScoreBoardDao.class)
                .getResultList();

    }
}


