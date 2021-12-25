package by.andrus.askit.dto.request;

public class CreateQuestionRequest {
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
        return this.getClass().getSimpleName() + "{" +
                "text='" + text + '\'' +
                ", askTime='" + askTime + '\'' +
                '}';
    }
}
