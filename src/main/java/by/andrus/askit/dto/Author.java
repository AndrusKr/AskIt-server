package by.andrus.askit.dto;

public class Author {
    public String id;
    public String nickname;

    public Author(Long id, String nickname) {
        this.id = id.toString();
        this.nickname = nickname;
    }
}
