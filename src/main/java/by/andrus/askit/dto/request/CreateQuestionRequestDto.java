package by.andrus.askit.dto.request;

public class CreateQuestionRequestDto {
    private String text;
    private String askTime;

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
