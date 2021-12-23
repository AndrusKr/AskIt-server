package by.andrus.askit.dto.request;

public class CreateQuestionRequestDto {
    private String text;
    private String askTime;

    public void setText(String text) {
        this.text = text;
    }

    public void setAskTime(String askTime) {
        this.askTime = askTime;
    }

    public String getText() {
        return text;
    }

    public String getAskTime() {
        return askTime;
    }

    @Override
    public String toString() {
        return "CreateQuestionRequestDto{" +
                "text='" + text + '\'' +
                ", askTime='" + askTime + '\'' +
                '}';
    }
}
