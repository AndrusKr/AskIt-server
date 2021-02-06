package by.andrus.askit.controller;

import by.andrus.askit.dto.QuestionDto;
import by.andrus.askit.service.QuestionService;
import by.andrus.askit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class QuestionsController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private QuestionService questionsService;
    @Autowired
    private UserService chatRoomService;

    @MessageMapping("/process-question")
    @SendTo("/topic/questions")
    public QuestionDto processQuestion(@Payload QuestionDto incomingQuestion) {
        System.out.println("Got: " + incomingQuestion);
        return incomingQuestion;
    }

//    @MessageMapping("/process-question")
//    @SendTo("/topic/questions")
//    public ResponseEntity<QuestionDto> processQuestion(@Payload QuestionDto incomingQuestion) {
//        System.out.println("Got: " + incomingQuestion);
//        questionsService.create(incomingQuestion.toQuestion());
//        Question createdQuestion;
//        try {
//            createdQuestion = this.questionsService.create(questionDto.toquestion());
//        } catch (Throwable throwable) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(QuestionDto.fromQuestion(createdQuestion), HttpStatus.CREATED);
//        // var chatId = chatRoomService.getChatId(question.getSenderId(), question.getRecipientId(), true); question.setChatId(chatId.get());
//        // Question saved = questionsService.save(question);
//        // messagingTemplate.convertAndSendToUser(question.getRecipientId(), "/queue/questions",
//        //     new Question(saved.getId(), saved.getSenderId(), saved.getSenderName()));
//    }

//    @PreAuthorize("hasAuthority('OWN_QUESTIONS_CREATE')")
//    @MessageMapping("/questions/create")
//    @SendTo("/topic/questions/created")
//    public QuestionDto create(QuestionDto questionDto) {
//        return questionsService.create(questionDto.toQuestion());
//    }
//
//    @PreAuthorize("hasAuthority('ALL_QUESTIONS_READ')")
//    @SubscribeMapping("/questions/get")
//    public List<QuestionDto> getAll() {
//        return questionsService.findAll();
//    }
//
//    // ToDo Question author to match the logged user's credentials
//    @PreAuthorize("hasAuthority('ALL_QUESTIONS_UPDATE') or #question.getAuthor() == principal.id")
//    @SubscribeMapping("/questions/update/{id}")
//    public Long update(@DestinationVariable @P("question") QuestionDto questionDto) {
//        return questionsService.update(questionDto.toQuestion());
//    }
//
//    // ToDo Question author to match the logged user's credentials
//    @PreAuthorize("hasAuthority('ALL_QUESTIONS_DELETE') or #questionAuthorID == principal.id")
//    @SubscribeMapping("/questions/delete/{id}")
//    public Long delete(@DestinationVariable @P("questionAuthorID") Long id) {
//        return questionsService.delete(id);
//    }

}