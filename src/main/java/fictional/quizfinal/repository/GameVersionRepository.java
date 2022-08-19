package fictional.quizfinal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fictional.quizfinal.entity.GameVersion;

@Repository
public interface GameVersionRepository extends JpaRepository<GameVersion, Integer> {

    @Query(value = "SELECT MAX(g.idVersion) FROM GameVersion g")
    Optional<Integer> fetchMaxId();
}
