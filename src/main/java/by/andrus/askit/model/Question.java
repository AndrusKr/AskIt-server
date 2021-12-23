package by.andrus.askit.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "answered")
    private Date answerTime;

    @Column(name = "asked", nullable = false)
    private Date askTime;

    @Column(name = "last_edited_at")
    private Date lastEditedAt;

    @Column(name = "user_id")
    private Long authorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User author;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Optional<Date> getAnswerTime() {
        return Optional.ofNullable(answerTime);
    }

    public void setAnswerTime(Date answered) {
        this.answerTime = answered;
    }

    public Date getAskTime() {
        return askTime;
    }

    public void setAskTime(Date asked) {
        this.askTime = asked;
    }

    public Optional<Date> getLastEditedAt() {
        return Optional.ofNullable(lastEditedAt);
    }

    public void setLastEditedAt(Date last_edited_at) {
        this.lastEditedAt = last_edited_at;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
