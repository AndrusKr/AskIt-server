package by.andrus.askit.dto;

import by.andrus.askit.model.User;
import by.andrus.askit.model.enums.Role;
import by.andrus.askit.model.enums.Status;
import lombok.Data;

import java.util.UUID;

@Data
public class AuthRequestDto {
    private String nickname;

    public User toUser() {
        User user = new User();
        user.setNickname(nickname == null ? "Anonymous" : nickname);
        user.setJwtSecret(UUID.randomUUID());
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        return user;
    }
}
