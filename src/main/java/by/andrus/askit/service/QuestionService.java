package by.andrus.askit.service;

import by.andrus.askit.dto.request.CreateQuestionRequest;
import by.andrus.askit.model.Question;
import by.andrus.askit.model.User;
import by.andrus.askit.repository.QuestionsRepository;
import by.andrus.askit.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionService.class);

    private final QuestionsRepository questionsRepository;
    private final SecurityContextHolderService securityContextHolderService;

    public QuestionService(QuestionsRepository questionsRepository, SecurityContextHolderService securityContextHolderService) {
        this.questionsRepository = questionsRepository;
        this.securityContextHolderService = securityContextHolderService;
    }

    public Question create(CreateQuestionRequest dto) {
        User initiator = securityContextHolderService.getWhoIsCalling();
        LOG.info("{} creating  question {}", initiator, dto);
        return questionsRepository.save(
                Question.builder().author(initiator)
                        .text(dto.getText())
                        .isPinned(false)
                        .askTime(TimeUtil.toDate(dto.getAskTime()))
                        .likes(List.of())
                        .build()
        );
    }

}
