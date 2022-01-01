CREATE TABLE questions
(
    id             uuid PRIMARY KEY            NOT NULL,
    text           VARCHAR(255)                NOT NULL,
    is_pinned      BOOLEAN                     NOT NULL,
    answer_time    TIMESTAMP WITHOUT TIME ZONE NULL,
    ask_time       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_edited_at TIMESTAMP WITHOUT TIME ZONE NULL,
    author_id      BIGINT                      NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users (id)
);