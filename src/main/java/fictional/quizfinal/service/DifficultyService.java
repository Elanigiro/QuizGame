package fictional.quizfinal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fictional.quizfinal.entity.Difficulty;
import fictional.quizfinal.repository.DifficultyRepository;

@Service
public class DifficultyService {
    
    @Autowired DifficultyRepository difficultyRepository;

    public Optional<Difficulty> fetchDifficulty(int id) {

        return difficultyRepository.findById(id);
    }

    public boolean isValidDifficulty(int id) {

        return fetchDifficulty(id).isPresent();
    }
}
