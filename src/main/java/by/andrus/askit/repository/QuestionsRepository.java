package by.andrus.askit.repository;

import by.andrus.askit.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, UUID> {
}
