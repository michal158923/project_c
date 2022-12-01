package com.example.project_c.model.quiz;

import com.example.project_c.model.home.MyUser;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "given_answer_id")
    private Answer givenAnswer;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUser user;
    @ManyToOne
    @JoinColumn(name = "lot_question_id")
    private Question lotQuestion;
    private boolean correctChoiceFlag;


}
