package com.example.project_c.model.home;

import com.example.project_c.model.quiz.random_code.RandomCode;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String code;
    private LocalDateTime joinDate;

    public MyUser(Long id, String name, String password, String code, LocalDateTime joinDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.code = new RandomCode(1,1,1,0,20).generate();
        this.joinDate = joinDate;
    }
}
