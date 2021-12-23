package by.andrus.askit.dto.request;

import by.andrus.askit.model.Question;
import by.andrus.askit.util.TimeUtil;

public class CreateQuestionRequestDto {
    private String text;
    private String askTime;

    public void setText(String text) {
        this.text = text;
    }

    public void setAskTime(String askTime) {
        this.askTime = askTime;
    }

    public Question toQuestion(String authorId) {
        Question q = new Question();
        q.setAuthorId(Long.valueOf(authorId));
        q.setText(text);
        q.setAskTime(TimeUtil.toDate(askTime));
        q.setLastEditedAt(TimeUtil.toDate(askTime));
        return q;
    }

    @Override
    public String toString() {
        return "QuestionRequestDto{" +
                "text='" + text + '\'' +
                ", askTime='" + askTime + '\'' +
                '}';
    }
}
