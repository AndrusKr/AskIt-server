package by.andrus.askit.dto.response;

public class QuestionOperationResponse {
    private final QuestionOperationType type;
    private final Object payload;
    private final String errorMessage;

    public QuestionOperationResponse(QuestionOperationType type, Object payload) {
        this.type = type;
        this.payload = payload;
        this.errorMessage = null;
    }

    public QuestionOperationResponse(QuestionOperationType type, String errorMessage) {
        this.type = type;
        this.errorMessage = errorMessage;
        this.payload = null;
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
