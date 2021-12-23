package by.andrus.askit.service;

import by.andrus.askit.dto.request.CreateQuestionRequestDto;
import by.andrus.askit.dto.response.CreateQuestionResponseDto;
import by.andrus.askit.model.Question;
import by.andrus.askit.model.User;
import by.andrus.askit.repository.QuestionsRepository;
import by.andrus.askit.repository.UsersRepository;
import by.andrus.askit.util.TimeUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionsRepository questionsRepository;
    private final UsersRepository usersRepository;

    public QuestionService(QuestionsRepository questionsRepository, UsersRepository usersRepository) {
        this.questionsRepository = questionsRepository;
        this.usersRepository = usersRepository;
    }

    public Question create(CreateQuestionRequestDto dto, String authorId) {
        Question askedQuestion = new Question();
        Optional<User> mayBeAuthor = usersRepository.findById(Long.valueOf(authorId));
        mayBeAuthor.ifPresent(askedQuestion::setAuthor);
        askedQuestion.setText(dto.getText());
        askedQuestion.setAskTime(TimeUtil.toDate(dto.getAskTime()));
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
