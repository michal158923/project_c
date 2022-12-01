CREATE TABLE my_user (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(20),
    password VARCHAR(60),
    code VARCHAR(20),
    join_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE answer (
    id INT AUTO_INCREMENT NOT NULL,
    question_id INT NOT NULL,
    content VARCHAR(200),
    correct_flag BIT,
    PRIMARY KEY (id)
);

CREATE TABLE question (
    id INT AUTO_INCREMENT NOT NULL,
    content VARCHAR(200),
    PRIMARY KEY (id)
);

CREATE TABLE user_answer (
    id INT AUTO_INCREMENT NOT NULL,
    user_id INT,
    lot_question_id INT,
    given_answer_id INT,
    correct_choice_flag BIT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES my_user(id),
    FOREIGN KEY (lot_question_id) REFERENCES question(id),
    FOREIGN KEY (given_answer_id) REFERENCES answer(id)
);