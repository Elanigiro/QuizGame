package fictional.quizfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fictional.quizfinal.entity.DiffVersion;
import fictional.quizfinal.entity.composite.DiffVersionId;

@Repository
public interface Diff_VersionRepository extends JpaRepository<DiffVersion, DiffVersionId> {
    
}
