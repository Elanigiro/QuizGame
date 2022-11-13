package fictional.quizfinal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fictional.quizfinal.entity.Question;
import fictional.quizfinal.entity.Topic;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    long countByTopic(Topic t);    
    //extracts exactly one page of elements
    List<Question> findByTopic(Topic t, PageRequest pr);

    //findById eager
    @Query(value = "SELECT q FROM Question q INNER JOIN FETCH q.answers WHERE q.idQuest = :id")
    Optional<Question> myEagerFindById(@Param(value = "id") Integer id);
}
