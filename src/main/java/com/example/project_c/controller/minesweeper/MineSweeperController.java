package com.example.project_c.controller.minesweeper;

import com.example.project_c.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MineSweeperController {

    @Autowired
    private final MyUserService myUserService;

    @GetMapping("/mineSweeper")
    public String HtmlMIneSweeper(@RequestParam String userCode, Model model) throws Exception {
        String code = myUserService.isAuthenticatedProvidesCode(userCode);
        if (code == null) {
            model.addAttribute("info", "You have not authenticated, please enter yours credential");
            return "/login";
        } else {
            model.addAttribute("userCode", code);
            return "/mineSweeper/mineSweeper";
        }
    }
    @GetMapping("/mineSweeperEnd")
    public String HtmlMIneSweeperEnd(@RequestParam String userCode, Model model) throws Exception {
        String code = myUserService.isAuthenticatedProvidesCode(userCode);
        if (code == null) {
            model.addAttribute("info", "You have not authenticated, please enter yours credential");
            return "/login";
        } else {
            model.addAttribute("userCode", code);
            return "/mineSweeper/mineSweeperEnd";
        }
    }
}
