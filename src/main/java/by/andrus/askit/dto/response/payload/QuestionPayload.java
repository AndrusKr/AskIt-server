package by.andrus.askit.dto.response.payload;

import by.andrus.askit.dto.AuthorDto;
import by.andrus.askit.model.Question;
import by.andrus.askit.util.TimeUtil;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionPayload {
    private String id;
    private String text;
    private Boolean isPinned;
    private String askTime;
    private String answerTime;
    private String lastEditedAt;
    private List<Long> likedUserIds;
    @JsonProperty("author")
    private AuthorDto authorDto;

    public QuestionPayload() {
    }

    public static List<QuestionPayload> from(List<Question> questions) {
        return questions.stream().map(QuestionPayload::from).collect(Collectors.toList());
    }

    public static QuestionPayload from(Question q) {
        QuestionPayload qD = new QuestionPayload();
        qD.setId(q.getId().toString());
        qD.setAuthorDto(new AuthorDto(q.getAuthor().getId(), q.getAuthor().getNickname()));
        qD.setText(q.getText());
        qD.setIsPinned(q.getIsPinned());
        qD.setAskTime(TimeUtil.toIsoString(q.getAskTime()));
        qD.setLikedUserIds(q.getLikedUserIds());
        q.getAnswerTime().ifPresent(t -> qD.setAnswerTime(TimeUtil.toIsoString(t)));
        q.getLastEditedAt().ifPresent(t -> qD.setEdited(TimeUtil.toIsoString(t)));
        return qD;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Boolean getPinned() {
        return isPinned;
    }

    public String getAskTime() {
        return askTime;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public String getLastEditedAt() {
        return lastEditedAt;
    }

    public List<Long> getLikedUserIds() {
        return likedUserIds;
    }

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthorDto(AuthorDto authorDto) {
        this.authorDto = authorDto;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIsPinned(Boolean pinned) {
        isPinned = pinned;
    }

    public void setAskTime(String askTime) {
        this.askTime = askTime;
    }

    public void setLikedUserIds(List<Long> likedUserIds) {
        this.likedUserIds = likedUserIds;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

    public void setEdited(String lastEditedAt) {
        this.lastEditedAt = lastEditedAt;
    }

}
