package fictional.quizfinal.payload.request;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fictional.quizfinal.payload.response.QuestionListResponse;
import fictional.quizfinal.service.Diff_VersionService;
import fictional.quizfinal.service.DifficultyService;
import fictional.quizfinal.service.GameVersionService;
import fictional.quizfinal.service.TopicService;
import fictional.quizfinal.utility.validation.ValidationStep1;
import fictional.quizfinal.utility.validation.ValidationStep2;
import fictional.quizfinal.utility.validation.ValidationStep3;

@Configurable (dependencyCheck=true)
public class UserScoreRequest {

    @Autowired DifficultyService difficultyService;
    @Autowired GameVersionService gameVersionService;
    @Autowired TopicService topicService;
    @Autowired Diff_VersionService diff_VersionService;

    @JsonIgnore
    public static final int USERNAME_MAX_LEN = 12;

    @NotBlank(message = "missing username", groups = ValidationStep1.class)
    @Size(max = USERNAME_MAX_LEN, message = "username '${validatedValue}' exceeds max length ({max})", groups = ValidationStep1.class)
    @Pattern(regexp = "[^\\s]+", message = "username '${validatedValue}' contains whitespaces", groups = ValidationStep1.class)
    private String username;

    private int result;
    private int difficulty;
    private int topic;
    private int gameVersion;

    @AssertTrue(message = "Invalid difficulty", groups = ValidationStep2.class)
    public boolean isValidDifficulty() {

        return difficultyService.isValidDifficulty(difficulty);
    }

    @AssertTrue(message = "Invalid game version", groups = ValidationStep2.class)
    public boolean isValidGameVersion() {

        return gameVersionService.isValidGameVersion(gameVersion);
    }

    @AssertTrue(message = "Invalid topic", groups = ValidationStep2.class)
    public boolean isValidTopic() {

        return topicService.isValidTopic(topic);
    }

    @AssertTrue(message = "Invalid result", groups = ValidationStep3.class)
    public boolean isValidResult() {

        int maxPossibleScore = QuestionListResponse.QUESTION_POOL_SIZE * (int)diff_VersionService.fetchScore(difficulty, gameVersion);

        // check if within valid range
        return ((this.result >= 0) && (this.result <= maxPossibleScore));
    }

    public String getUsername() {
        return username;
    }

    public int getResult() {
        return result;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getTopic() {
        return topic;
    }

    public int getGameVersion() {
        return gameVersion;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }

    public void setGameVersion(int gameVersion) {
        this.gameVersion = gameVersion;
    }
}
