package fictional.quizfinal.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fictional.quizfinal.entity.Question;
import fictional.quizfinal.entity.Topic;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    long countByTopic(Topic t);    
    //extracts exactly one page of elements
    List<Question> findByTopic(Topic t, PageRequest pr);
}
