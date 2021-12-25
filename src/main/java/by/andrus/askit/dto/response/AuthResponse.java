package by.andrus.askit.dto.response;

public class AuthResponse {
    public String id;
    public String nickname;
    public String jwt;

    public AuthResponse(Long id, String nickname, String jwt) {
        this.id = id.toString();
        this.nickname = nickname;
        this.jwt = jwt;
    }
}
