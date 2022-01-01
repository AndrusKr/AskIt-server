package by.andrus.askit.model;

import by.andrus.askit.model.embeddable.LikeId;
import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "questions")
@Builder
public class Question {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "is_pinned", nullable = false)
    private Boolean isPinned;

    @Column(name = "answered")
    private Date answerTime;

    @Column(name = "asked", nullable = false)
    private Date askTime;

    @Column(name = "last_edited_at")
    private Date lastEditedAt;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(mappedBy = "question", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Like> likes;

    public Question() {
    }

    public Question(UUID id, String text, Boolean isPinned, Date answerTime, Date askTime, Date lastEditedAt, User author, List<Like> likes) {
        this.id = id;
        this.text = text;
        this.isPinned = isPinned;
        this.answerTime = answerTime;
        this.askTime = askTime;
        this.lastEditedAt = lastEditedAt;
        this.author = author;
        this.likes = likes;
    }

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

    public Boolean getIsPinned() {
        return isPinned;
    }

    public void setIsPinned(Boolean isPinned) {
        this.isPinned = isPinned;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User user) {
        this.author = user;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Long> getLikedUserIds() {
        return likes.stream().map(Like::getId).map(LikeId::getUserId).collect(Collectors.toList());
    }
}
