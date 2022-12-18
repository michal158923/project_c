package com.example.project_c.controller.home;


import com.example.project_c.controller.home.dto.CredentialDto;
import com.example.project_c.controller.home.dto.Info;
import com.example.project_c.model.home.MyUser;
import com.example.project_c.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class HomeController {

    private static final Long EMPTY_ID = null;
    private static final String EMPTY_CODE = null;
    @Autowired
    private final MyUserService myUserService;

    @GetMapping("/home")
    public String HtmlHome() {
        return "/home";
    }

    @GetMapping("/register")
    public String HtmlRegister() {
        return "/register";
    }

    @PostMapping("/register/user")
    public @ResponseBody Info registerUser(@RequestBody CredentialDto credentialDto) throws Exception {
        MyUser givenUser = MyUser.builder()
                .id(EMPTY_ID)
                .name(credentialDto.getName())
                .password(credentialDto.getPassword())
                .code(EMPTY_CODE)
                .joinDate(LocalDateTime.now())
                .build();
        return myUserService.register(givenUser);
    }

    @GetMapping("/login")
    public String HtmlLogin() {
        return "/login";
    }

    @PostMapping("/login/user")
    public @ResponseBody Info htmlLoginUser(Model model, @RequestBody CredentialDto credentialDto) throws Exception {
        String userCode = myUserService.isAuthorized(credentialDto);
        if (userCode == null)
            return new Info("none", "password and login do not match");
        else
            return new Info(userCode, "logged successfully");
    }

    @GetMapping("/homeForUser")
    public String HtmlHomeForUsers(@RequestParam String userCode, Model model) throws Exception {
        String code = myUserService.isAuthenticatedProvidesCode(userCode);//code==null
        if (code == null) {
            model.addAttribute("info", "You have not authenticated, please enter yours credential");
            return "/login";
        } else {
            model.addAttribute("userCode", code);
            return "/homeForUser";
        }
    }
}
