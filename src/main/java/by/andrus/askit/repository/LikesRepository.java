package by.andrus.askit.repository;

import by.andrus.askit.model.Like;
import by.andrus.askit.model.embeddable.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Like, LikeId> {
}
