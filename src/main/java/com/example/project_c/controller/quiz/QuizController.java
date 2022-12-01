package com.example.project_c.controller.quiz;

import com.example.project_c.controller.quiz.dto.QuestionAndAnswersResponse;
import com.example.project_c.model.home.MyUser;
import com.example.project_c.service.MyUserService;
import com.example.project_c.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
}
