package fictional.quizfinal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fictional.quizfinal.entity.DiffVersion;
import fictional.quizfinal.entity.composite.DiffVersionId;
import fictional.quizfinal.repository.Diff_VersionRepository;

@Service
@Transactional
public class Diff_VersionService {
    
    @Autowired Diff_VersionRepository diff_VersionRepository;

    public Optional<DiffVersion> fetchScore(int diff, int version) {

        return diff_VersionRepository.findById(new DiffVersionId(diff, version));
    }
}
