package fictional.quizfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fictional.quizfinal.entity.composite.DiffVersionId;
import fictional.quizfinal.repository.Diff_VersionRepository;

@Service
@Transactional
public class Diff_VersionService {
    
    @Autowired Diff_VersionRepository diff_VersionRepository;

    public Integer fetchScore(int diff, int version) {

        var res = diff_VersionRepository.findById(new DiffVersionId(diff, version));

        if (res.isPresent()) {

            return res.get().getScorePerQuest();
        }
        else {

            return null;
        }
    }
}
