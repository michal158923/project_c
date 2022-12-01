package com.example.project_c.service;

import com.example.project_c.controller.quiz.dto.QuestionAndAnswersResponse;
import com.example.project_c.model.home.MyUser;
import com.example.project_c.model.quiz.Question;
import com.example.project_c.model.quiz.UserAnswer;
import com.example.project_c.model.quiz.dao.AnswerDao;
import com.example.project_c.model.quiz.dao.QuestionAndAnswers;
import com.example.project_c.model.quiz.dao.QuestionDao;
import com.example.project_c.model.quiz.random_code.RandomCode;
import com.example.project_c.repository.home.MyUserRepository;
import com.example.project_c.repository.quiz.AnswerRepository;
import com.example.project_c.repository.quiz.QuestionRepository;
import com.example.project_c.repository.quiz.UserAnswerRepositoriesImpl;
import com.example.project_c.repository.quiz.UserAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuizService {

    private static final int NUMBER_OF_ALL_QUESTIONS_IN_DB = 20; // zmienic po dodaniu metody dodającej lub usuwającej pytania
    private static final int QUIZ_LENGTH = 8;

    @Autowired
    private final MyUserRepository myUserRepository;
    @Autowired
    private final AnswerRepository answerRepository;
    @Autowired
    private final QuestionRepository questionRepository;
    @Autowired
    private final UserAnswerRepository userAnswerRepository;
    @Autowired
    private final UserAnswerRepositoriesImpl userAnswerRepositoriesImpl;


    public QuestionAndAnswersResponse next(MyUser user) {
        List<QuestionAndAnswers> questionAndAnswers = userAnswerRepositoriesImpl.findQuestionAndAnswersForUser(user);
        if (questionAndAnswers.size() == 0) {
            createEmptyUserAnswerSet(user);
            questionAndAnswers = userAnswerRepositoriesImpl.findQuestionAndAnswersForUser(user);
        }


        Map<QuestionDao, List<AnswerDao>> mapQuestionAnswerListDao = questionAndAnswers.stream()
                .collect(
                        Collectors.groupingBy(QuestionAndAnswers::getQuestionDao,
                                Collectors.mapping(QuestionAndAnswers::getAnswerDao,Collectors.toList()))
                );

        return null;
    }

    private void createEmptyUserAnswerSet(MyUser user) {
        List<UserAnswer> userAnswerList = new ArrayList<>();
        List<Question> allQuestions = questionRepository.findAll();
        int[] questionIds = RandomCode.randomNumberByRange(NUMBER_OF_ALL_QUESTIONS_IN_DB, QUIZ_LENGTH);
        for (int qi : questionIds)
            userAnswerList.add(UserAnswer.builder()
                    .user(user)
                    .lotQuestion(allQuestions.get(qi))
                    .build());
        userAnswerRepository.saveAll(userAnswerList);
    }
}
