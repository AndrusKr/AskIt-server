CREATE TABLE questions
(
    id             uuid PRIMARY KEY                          NOT NULL,
    text           VARCHAR(255)                              NOT NULL,
    answered       TIMESTAMP WITHOUT TIME ZONE               NULL,
    asked          TIMESTAMP WITHOUT TIME ZONE               NOT NULL,
    last_edited_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
--     author BIGINT                                    NOT NULL,
    author         uuid                                      NOT NULL
--     FOREIGN KEY (author) REFERENCES users (id)
);