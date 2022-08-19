package fictional.quizfinal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fictional.quizfinal.entity.QuizUser;

@Repository
public interface QuizUserRepository extends JpaRepository<QuizUser, Integer> {
    
    Optional<QuizUser> findByNickname(String nickname);
}
