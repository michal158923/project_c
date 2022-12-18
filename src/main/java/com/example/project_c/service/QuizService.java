package com.example.project_c.service;

import com.example.project_c.controller.quiz.dto.*;
import com.example.project_c.model.home.MyUser;
import com.example.project_c.model.quiz.Answer;
import com.example.project_c.model.quiz.Question;
import com.example.project_c.model.quiz.UserAnswer;
import com.example.project_c.model.quiz.dao.AnswerDao;
import com.example.project_c.model.quiz.dao.QuestionAndAnswers;
import com.example.project_c.model.quiz.dao.QuestionDao;
import com.example.project_c.model.quiz.dao.ScoreBoardDao;
import com.example.project_c.model.quiz.random_code.RandomCode;
import com.example.project_c.repository.home.MyUserRepository;
import com.example.project_c.repository.quiz.AnswerRepository;
import com.example.project_c.repository.quiz.QuestionRepository;
import com.example.project_c.repository.quiz.QuizRepositoriesImpl;
import com.example.project_c.repository.quiz.UserAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
    private final QuizRepositoriesImpl quizRepositoriesImpl;


    public QuestionAndAnswersResponse next(MyUser user) {
        List<QuestionAndAnswers> questionAndAnswers = quizRepositoriesImpl.findQuestionAndAnswersForUser(user);
        if (questionAndAnswers.size() == 0) {
            createEmptyUserAnswerSet(user);
            questionAndAnswers = quizRepositoriesImpl.findQuestionAndAnswersForUser(user);
        }
        Map<QuestionDao, List<AnswerDao>> mapQuestionNullAnswerListDao = questionAndAnswers.stream()
                .filter(qaa -> qaa.getGivenAnswerId() == null)
                .collect(
                        Collectors.groupingBy(QuestionAndAnswers::getQuestionDao,
                                Collectors.mapping(QuestionAndAnswers::getAnswerDao, Collectors.toList())));
        int leftQuestionNumber = QUIZ_LENGTH - mapQuestionNullAnswerListDao.size() + 1;
        if (leftQuestionNumber != QUIZ_LENGTH + 1)
            return questionAnswerToDto(mapQuestionNullAnswerListDao, user, leftQuestionNumber);
        return QuestionAndAnswersResponse.builder().questionCount("quiz has been finished").build();
    }

    private QuestionAndAnswersResponse questionAnswerToDto(Map<QuestionDao, List<AnswerDao>> mapQuestionNullAnswerListDao, MyUser user, int leftQuestionNumber) {
        QuestionAndAnswersResponse questionAndAnswersResponse = new QuestionAndAnswersResponse();
        for (Map.Entry<QuestionDao, List<AnswerDao>> entry : mapQuestionNullAnswerListDao.entrySet()) {
            QuestionDto questionDto = QuestionDto.builder()
                    .Id(entry.getKey().getId())
                    .content(entry.getKey().getContent())
                    .build();
            List<AnswerDto> answerDtoList = entry.getValue().stream()
                    .map(answerDao -> AnswerDto.builder()
                            .id(answerDao.getId())
                            .content(answerDao.getContent())
                            .build())
                    .collect(Collectors.toList());
            questionAndAnswersResponse = QuestionAndAnswersResponse.builder()
                    .userCode(user.getCode())
                    .questionCount(leftQuestionNumber + "/" + QUIZ_LENGTH)
                    .questionDto(questionDto)
                    .answers(answerDtoList)
                    .build();
            break;
        }
        return questionAndAnswersResponse;
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

    public Info checkGivenAnswer(MyUser user, GivenAnswerRequest givenAnswerRequest) {
        Answer answer = answerRepository.findById(givenAnswerRequest.getGivenAnswerId()).orElseThrow();
        quizRepositoriesImpl.updateUserAnswer(
                user.getId(),
                givenAnswerRequest.getQuestionId(),
                givenAnswerRequest.getGivenAnswerId(),
                answer.isCorrectFlag());

        if (answer.isCorrectFlag())
            return new Info("correct");
        return new Info("not correct");
    }

    public List<ScoreBoardDto> getScoreBoard() {

        Map<String, Long> scoreboardMap = quizRepositoriesImpl.userAnswerJoinUser().stream()
                .filter(ScoreBoardDao::isCorrectChoiceFlag)
                .collect(Collectors.groupingBy(ScoreBoardDao::getUserName, Collectors.counting()));

        List<ScoreBoardDto> scoreBoard = new ArrayList<>();
        for (Map.Entry<String, Long> entry : scoreboardMap.entrySet()) {
            scoreBoard.add(ScoreBoardDto.builder()
                    .userName(entry.getKey())
                    .scores(entry.getValue().toString())
                    .build());
        }
        return scoreBoard.stream()
                .sorted(Comparator.comparing(ScoreBoardDto::getScores).reversed())
                .collect(Collectors.toList());
    }
}
