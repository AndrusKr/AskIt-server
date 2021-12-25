package by.andrus.askit.controller;

import by.andrus.askit.dto.request.CreateQuestionRequest;
import by.andrus.askit.dto.request.UpdateQuestionRequest;
import by.andrus.askit.dto.response.QuestionOperationResponse;
import by.andrus.askit.dto.response.QuestionOperationType;
import by.andrus.askit.dto.response.payload.CreateQuestionPayload;
import by.andrus.askit.model.Question;
import by.andrus.askit.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class QuestionsController {
    private static final Logger LOG = LoggerFactory.getLogger(QuestionsController.class);

    @Autowired
    private QuestionService questionsService;

    @MessageMapping("/question/create")
    public QuestionOperationResponse processQuestion(@Payload CreateQuestionRequest createQuestionRequest) {
        Question createdQuestion = questionsService.create(createQuestionRequest);
        return new QuestionOperationResponse(QuestionOperationType.CREATE, Map.of("createdQuestion", CreateQuestionPayload.fromQuestion(createdQuestion)));
    }

//    @PreAuthorize("hasAuthority('ALL_QUESTIONS_READ')")
//    @MessageMapping("/questions/get")
//    public List<CreateQuestionPayload> getAll() {
//        return questionsService.findAll();
//    }
//
//    @PreAuthorize("hasAuthority('ALL_QUESTIONS_UPDATE') or #question.getAuthor() == principal.id")
//    @MessageMapping("/questions/update")
//    public QuestionOperationResponse update(@DestinationVariable @P("question") UpdateQuestionRequest questionDto) {
//        Question updatedQuestion = questionsService.update(questionDto);
//        return new QuestionOperationResponse(QuestionOperationType.UPDATE, Map.of("updatedQuestion", updatedQuestion));
//    }
//
//    // ToDo Question author to match the logged user's credentials
//    @PreAuthorize("hasAuthority('ALL_QUESTIONS_DELETE') or #questionAuthorID == principal.id")
//    @MessageMapping("/questions/delete")
//    public QuestionOperationResponse delete(@DestinationVariable @P("questionAuthorID") UUID id) {
//        questionsService.delete(id);
//        return new QuestionOperationResponse(QuestionOperationType.DELETE, Map.of("deletedQuestion", id));
//    }

}