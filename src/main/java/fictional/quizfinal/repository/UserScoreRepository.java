package fictional.quizfinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fictional.quizfinal.dto.ILeaderboardEntry;
import fictional.quizfinal.entity.QuizUser;
import fictional.quizfinal.entity.UserScore;

@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, Integer> {
    
    List<UserScore> findByQuizuserOrderByTotalScore(QuizUser user);

    @Query(value = "SELECT nickname, score FROM LeaderboardOfVersion WHERE game_version = :version FETCH FIRST 100 ROWS ONLY", nativeQuery = true)
    List<ILeaderboardEntry> fetchLeaderboard(@Param(value = "version") int version);
}
