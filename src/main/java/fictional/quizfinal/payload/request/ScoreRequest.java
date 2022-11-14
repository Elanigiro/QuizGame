package fictional.quizfinal.payload.request;

import javax.validation.constraints.AssertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import fictional.quizfinal.service.Diff_VersionService;
import fictional.quizfinal.service.GameVersionService;
import fictional.quizfinal.utility.validation.ValidationStep1;
import fictional.quizfinal.utility.validation.ValidationStep2;

@Configurable (dependencyCheck=true)
public class ScoreRequest {

    @Autowired GameVersionService gameVersionService;
    @Autowired Diff_VersionService diffVersionService;

    int version;
    int difficulty;

    public ScoreRequest(int difficulty) {
        this.difficulty = difficulty;
    }

    public ScoreRequest() {
    }

    @AssertTrue(message = "Invalid game version", groups = ValidationStep1.class)
    boolean isValidVersion() {

        return gameVersionService.fetchGameVersion(version).isPresent();
    }

    @AssertTrue(message = "Invalid difficulty", groups = ValidationStep2.class)
    boolean isValidDifficulty() {

        return diffVersionService.fetchScore(difficulty, version).isPresent();
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