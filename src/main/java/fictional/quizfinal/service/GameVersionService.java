package fictional.quizfinal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fictional.quizfinal.entity.GameVersion;
import fictional.quizfinal.repository.GameVersionRepository;

@Service
public class GameVersionService {
    
    @Autowired GameVersionRepository gameVersionRepository;

    public Optional<Integer> fetchLatestVersionId() {

        return gameVersionRepository.fetchMaxId();
    }

    public Optional<GameVersion> fetchGameVersion(int id) {

        return gameVersionRepository.findById(id);
    }

    public boolean isValidGameVersion(int id) {

        return fetchGameVersion(id).isPresent();
    }
}
