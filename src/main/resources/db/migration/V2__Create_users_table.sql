create TABLE users
(
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    nickname    VARCHAR(20)           NOT NULL,
    jwt_secret  uuid                  NOT NULL,
    user_status status                NOT NULL,
    user_role   roles                 NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    updated_at  TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);