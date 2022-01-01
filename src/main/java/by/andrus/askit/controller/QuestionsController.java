package by.andrus.askit.controller;

import by.andrus.askit.dto.request.CreateQuestionRequest;
import by.andrus.askit.dto.response.OpResponse;
import by.andrus.askit.dto.response.payload.QuestionPayload;
import by.andrus.askit.model.Question;
import by.andrus.askit.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import static by.andrus.askit.dto.response.QuestionOperationType.CREATE;
import static by.andrus.askit.dto.response.QuestionOperationType.READ_ALL;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
@SendTo("/topic/questions")
public class QuestionsController {
    private static final Logger LOG = LoggerFactory.getLogger(QuestionsController.class);

    private final QuestionService questionsService;

    public QuestionsController(QuestionService questionsService) {
        this.questionsService = questionsService;
    }

    @MessageMapping("/question/create")
    public OpResponse create(@Payload CreateQuestionRequest createQuestionRequest) {
        Question createdQuestion = questionsService.create(createQuestionRequest);
        return new OpResponse(CREATE, QuestionPayload.from(createdQuestion));
    }

    @PreAuthorize("hasAuthority('ALL_QUESTIONS_READ')")
    @MessageMapping("/questions/get")
    public OpResponse getAll() {
        return new OpResponse(READ_ALL, QuestionPayload.from(questionsService.findAll()));
    }

//    @PreAuthorize("hasAuthority('ALL_QUESTIONS_UPDATE') or #question.getAuthor() == principal.id")
//    @MessageMapping("/questions/update")
//    public OpResponse update(UpdateQuestionRequest questionDto) {
//        Question updatedQuestion = questionsService.update(questionDto);
//        return new OpResponse(UPDATE, QuestionPayload.from(updatedQuestion));
//    }
//
//    // ToDo Question author to match the logged user's credentials
//    @PreAuthorize("hasAuthority('ALL_QUESTIONS_DELETE') or #questionAuthorID == principal.id")
//    @MessageMapping("/questions/delete")
//    public OpResponse delete(UUID questionId) {
//        questionsService.delete(questionId);
//        return new OpResponse(DELETE, questionId);
//    }
}