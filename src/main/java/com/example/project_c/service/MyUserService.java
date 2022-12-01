package com.example.project_c.service;

import java.util.List;
import java.util.Objects;

import com.example.project_c.controller.home.dto.CredentialDto;
import com.example.project_c.controller.home.dto.Info;
import com.example.project_c.model.home.MyUser;
import com.example.project_c.repository.home.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserService {

    @Autowired
    private final MyUserRepository myUserRepository;

    public Info register(MyUser givenUser) throws Exception {
        List<MyUser> userFromDb = myUserRepository.findAllByName(givenUser.getName());
        if (userFromDb.size() > 1)
            throw new Exception("found more than one users with the same name");
        if (userFromDb.size() == 1)
            return new Info("none", "user name already taken");
        return new Info(
                myUserRepository.save(givenUser).getCode(),
                "new user created successfully");

    }

    public String isAuthorized(CredentialDto credentialDto) throws Exception {
        List<MyUser> userFromDb = myUserRepository.findAllByName(credentialDto.getName());
        if (userFromDb.size() == 0)
            return null;
        if (userFromDb.size() > 1)
            throw new Exception("found more than one users with the same name");
        if (Objects.equals(
                userFromDb.get(0).getPassword(),
                credentialDto.getPassword()))
            return userFromDb.get(0).getCode();
        else
            return null;
    }

    public String isAuthenticatedProvidesCode(String userCode) throws Exception {
        List<MyUser> userFromDb = myUserRepository.findAllByCode(userCode);
        if (userFromDb.size() > 1)
            throw new Exception("found more than one users with the same name");
        if (userFromDb.size() == 1)
            return userFromDb.get(0).getCode();
        return null;
    }
    public MyUser isAuthenticated(String userCode) throws Exception {
        List<MyUser> userFromDb = myUserRepository.findAllByCode(userCode);
        if (userFromDb.size() > 1)
            throw new Exception("found more than one users with the same name");
        if (userFromDb.size() == 1)
            return userFromDb.get(0);
        return null;
    }
}
