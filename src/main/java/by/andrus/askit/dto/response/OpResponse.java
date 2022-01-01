package by.andrus.askit.dto.response;

public class OpResponse {
    private final QuestionOperationType type;
    private final Object payload;
    private final String errorMessage;

    public OpResponse(QuestionOperationType type, Object payload) {
        this.type = type;
        this.payload = payload;
        this.errorMessage = null;
    }

    public QuestionOperationType getType() {
        return type;
    }

    public Object getPayload() {
        return payload;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
