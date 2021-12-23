package by.andrus.askit.dto.request;

import by.andrus.askit.model.User;
import by.andrus.askit.model.enums.Roles;
import by.andrus.askit.model.enums.Status;

import java.util.UUID;

public class AuthRequestDto {
    public String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public User toUser() {
        User user = new User();
        user.setNickname(nickname == null ? "Anonymous" : nickname);
        user.setJwtSecret(UUID.randomUUID());
        user.setRole(Roles.USER);
        user.setStatus(Status.ACTIVE);
        return user;
    }
}
