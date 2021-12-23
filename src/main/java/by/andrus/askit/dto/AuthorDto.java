package by.andrus.askit.dto;

public class AuthorDto {
    public String id;
    public String nickname;

    public AuthorDto(Long id, String nickname) {
        this.id = id.toString();
        this.nickname = nickname;
    }
}
