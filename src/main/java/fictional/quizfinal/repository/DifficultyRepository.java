package fictional.quizfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fictional.quizfinal.entity.Difficulty;

@Repository
public interface DifficultyRepository extends JpaRepository<Difficulty, Integer> {
    
}
