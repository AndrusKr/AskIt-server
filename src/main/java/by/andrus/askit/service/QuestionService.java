package by.andrus.askit.service;

import by.andrus.askit.dto.response.CreateQuestionResponseDto;
import by.andrus.askit.model.Question;
import by.andrus.askit.repository.QuestionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionsRepository questionsRepository;

    public QuestionService(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    public Question create(Question askedQuestion) {
        return questionsRepository.save(askedQuestion);
    }

    public List<CreateQuestionResponseDto> findAll() {
        return null;
    }

    public Long update(Question incomingQuestion) {
        return null;
    }

    public Long delete(Long id) {
        return null;
    }

}
