package by.andrus.askit.dto;

import by.andrus.askit.model.Question;
import by.andrus.askit.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionDto {
    private String id;
    private AuthorDto author;
    private String text;
    private String asked;
    private List<String> likes;
    private String answered;
    private String edited;

    public QuestionDto() {
    }

    public static QuestionDto fromQuestion(Question q) {
        QuestionDto qD = new QuestionDto();
        qD.setId(q.getId());
//        questionDto.setAuthor(new AuthorDto(q.getAuthor().getId().toString(), q.getAuthor().getNickname());
        qD.setAuthor(new AuthorDto(q.getAuthor().toString(), "temp"));
        qD.setText(q.getText());
        // "2018-05-04T21:42:22+00:00"
        qD.setAsked(TimeUtil.toIsoString(q.getAsked()));
//        questionDto.setLikes(q.getLikes());
        qD.setLikes(new ArrayList<>());
        qD.setAnswered(TimeUtil.toIsoString(q.getAnswered()));
        qD.setEdited(TimeUtil.toIsoString(q.getLast_edited_at()));
        return qD;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAsked() {
        return asked;
    }

    public void setAsked(String asked) {
        this.asked = asked;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public String getAnswered() {
        return answered;
    }

    public void setAnswered(String answered) {
        this.answered = answered;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public Question toQuestion() {
        Question q = new Question();
        q.setId(UUID.fromString(id));
        q.setAuthor(UUID.fromString(author.getId()));
        q.setText(text);
        q.setAsked(TimeUtil.toDate(asked));
        q.setAnswered(TimeUtil.toDate(answered));
        return q;
    }
}
