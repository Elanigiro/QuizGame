package fictional.quizfinal.payload.request;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fictional.quizfinal.entity.DiffVersion;
import fictional.quizfinal.service.Diff_VersionService;
import fictional.quizfinal.service.GameVersionService;
import fictional.quizfinal.utility.validation.ValidationStep1;
import fictional.quizfinal.utility.validation.ValidationStep2;

@Configurable (dependencyCheck=true)
public class ScoreRequest {

    @Autowired GameVersionService gameVersionService;
    @Autowired Diff_VersionService diffVersionService;

    @JsonIgnore
    private DiffVersion scoreObject;

    @NotNull
    private Integer version;
    @NotNull
    private Integer difficulty;

    @AssertTrue(message = "Invalid game version", groups = ValidationStep1.class)
    boolean isValidVersion() {

        return gameVersionService.fetchGameVersion(version).isPresent();
    }

    @AssertTrue(message = "Invalid difficulty", groups = ValidationStep2.class)
    boolean isValidDifficulty() {

        scoreObject = diffVersionService.fetchScore(difficulty, version).orElse(null);

        return scoreObject != null;
    }

    public ScoreRequest(int difficulty) {
        this.difficulty = difficulty;
    }

    public ScoreRequest() {
    }

    public DiffVersion getScoreObject() {
        return scoreObject;
    }

    public void setScoreObject(DiffVersion scoreObject) {
        this.scoreObject = scoreObject;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    } 
}
