CREATE TABLE questions
(
    id             uuid PRIMARY KEY            NOT NULL,
    text           VARCHAR(255)                NOT NULL,
    answerTime     TIMESTAMP WITHOUT TIME ZONE NULL,
    askTime        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_edited_at TIMESTAMP WITHOUT TIME ZONE NULL,
    user_id        BIGINT                      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);