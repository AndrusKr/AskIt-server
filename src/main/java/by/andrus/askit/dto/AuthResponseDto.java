package by.andrus.askit.dto;

public class AuthResponseDto {
    public String id;
    public String nickname;
    public String jwt;

    public AuthResponseDto(Long id, String nickname, String jwt) {
        this.id = id.toString();
        this.nickname = nickname;
        this.jwt = jwt;
    }
}
