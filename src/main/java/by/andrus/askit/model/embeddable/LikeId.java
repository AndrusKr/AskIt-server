package by.andrus.askit.model.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class LikeId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "question_id")
    private UUID questionId;

    public LikeId() {
    }

    public LikeId(Long userId, UUID questionId) {
        this.userId = userId;
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LikeId)) return false;

        LikeId likeId = (LikeId) o;

        if (!userId.equals(likeId.userId)) return false;
        return questionId.equals(likeId.questionId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + questionId.hashCode();
        return result;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }
}
