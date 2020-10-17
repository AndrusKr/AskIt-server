create TABLE users
(
    id           BIGSERIAL              PRIMARY KEY          NOT NULL,
    username     VARCHAR(255)                                NOT NULL,
    jwt_secret   uuid                                        NOT NULL,
    user_status  status                                      NOT NULL,
    user_role    roles                                       NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);