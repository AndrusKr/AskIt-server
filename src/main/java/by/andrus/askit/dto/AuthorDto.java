package by.andrus.askit.dto;


public class AuthorDto {
    public String authorId;
    public String authorNickname;

    public AuthorDto(Long authorId, String authorNickname) {
        this.authorId = authorId.toString();
        this.authorNickname = authorNickname;
    }
}
