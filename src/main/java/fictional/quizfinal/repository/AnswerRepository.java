package fictional.quizfinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fictional.quizfinal.entity.Answer;
import fictional.quizfinal.entity.Question;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    
    List<Answer> findByQuestion(Question q);
}
