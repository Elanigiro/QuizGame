package fictional.quizfinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fictional.quizfinal.dto.ILeaderboardEntry;
import fictional.quizfinal.entity.QuizUser;
import fictional.quizfinal.entity.UserScore;
import fictional.quizfinal.repository.UserScoreRepository;

@Service
@Transactional
public class UserScoreService {
    
    @Autowired UserScoreRepository userScoreRepository;

    public List<UserScore> fetchUserScores(QuizUser user) {

        return userScoreRepository.findByQuizuserOrderByTotalScore(user);
    }

    public UserScore saveUserScore(UserScore us) {

        return userScoreRepository.save(us);
    }

    public List<ILeaderboardEntry> fetchLeaderboard(int version) {

        return userScoreRepository.fetchLeaderboard(version);
    }
}
