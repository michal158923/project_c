package com.example.project_c.controller.quiz;

import com.example.project_c.controller.quiz.dto.GivenAnswerRequest;
import com.example.project_c.controller.quiz.dto.Info;
import com.example.project_c.controller.quiz.dto.QuestionAndAnswersResponse;
import com.example.project_c.controller.quiz.dto.ScoreBoardDto;
import com.example.project_c.model.home.MyUser;
import com.example.project_c.service.MyUserService;
import com.example.project_c.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuizController {
    @Autowired
    private final MyUserService myUserService;
    @Autowired
    private final QuizService quizService;

    @GetMapping("/quiz")
    public String HtmlQuiz(@RequestParam String userCode, Model model) throws Exception {
        String code = myUserService.isAuthenticatedProvidesCode(userCode);
        if (code == null) {
            model.addAttribute("info", "You have not authenticated, please enter yours credential");
            return "/login";
        } else {
            model.addAttribute("userCode", code);
            return "/quiz/quiz";
        }
    }
    @GetMapping("/quiz/next")
    public @ResponseBody QuestionAndAnswersResponse quizNext(@RequestParam String userCode) throws Exception {
        MyUser user = myUserService.isAuthenticated(userCode);
        if (user == null) {
            return null;
        } else {
            return quizService.next(user);
        }
    }
    @PostMapping("/quiz/checkAnswer")
    public @ResponseBody Info quizCheckGivenAnswer(@RequestParam String userCode, @RequestBody GivenAnswerRequest quizCheckGivenAnswer) throws Exception{
        MyUser user = myUserService.isAuthenticated(userCode);
        if (user == null) {
            return null;
        } else {
            return quizService.checkGivenAnswer(user, quizCheckGivenAnswer);
        }
    }
    @GetMapping("/quiz/end")
    public String HtmlQuizEnd(@RequestParam String userCode, Model model) throws Exception {
        String code = myUserService.isAuthenticatedProvidesCode(userCode);
        if (code == null) {
            model.addAttribute("info", "You have not authenticated, please enter yours credential");
            return "/login";
        } else {
            model.addAttribute("userCode", code);
            return "/quiz/quizEnd";
        }
    }
    @GetMapping("/quiz/end/data")
    public @ResponseBody List<ScoreBoardDto> quizEndData(@RequestParam String userCode) throws Exception {
        MyUser user = myUserService.isAuthenticated(userCode);
        if (user == null) {
            return null;
        } else {
            return quizService.getScoreBoard();
        }
    }//todo tu skończyłem można za pomoca thynmeleafa od razu wysłać htmla z tablica wy7ników
    // todo dlaczego podczas nieprawidłowego loginu i hasła wyrzuca exception

}
