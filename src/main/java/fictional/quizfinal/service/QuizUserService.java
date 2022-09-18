package fictional.quizfinal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fictional.quizfinal.entity.QuizUser;
import fictional.quizfinal.repository.QuizUserRepository;

@Service
@Transactional
public class QuizUserService {
    
    @Autowired QuizUserRepository quizUserRepository;

    public Optional<QuizUser> fetchUser(String nickname) {

        return quizUserRepository.findByNickname(nickname);
    }

    public QuizUser saveUser(QuizUser user) {

        return quizUserRepository.save(user);
    }
}
