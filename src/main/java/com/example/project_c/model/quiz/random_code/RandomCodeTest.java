package com.example.project_c.model.quiz.random_code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RandomCodeTest {
    public static void main(String[] args) throws Exception {

        log.info(new RandomCode(1,6,1,0,8).generate()) ;
    }
}
