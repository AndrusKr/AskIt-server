CREATE TABLE likes
(
    user_id     BIGINT                      NOT NULL,
    question_id uuid                        NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT LIKES_PKEY PRIMARY KEY (user_id, question_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (question_id) REFERENCES questions (id)
);