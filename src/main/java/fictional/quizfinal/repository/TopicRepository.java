package fictional.quizfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fictional.quizfinal.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    
}
