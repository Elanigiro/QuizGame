package fictional.quizfinal.payload.request;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import fictional.quizfinal.service.Diff_VersionService;
import fictional.quizfinal.service.DifficultyService;
import fictional.quizfinal.service.GameVersionService;
import fictional.quizfinal.service.TopicService;
import fictional.quizfinal.utility.validation.ValidationStep1;
import fictional.quizfinal.utility.validation.ValidationStep2;

@Configurable (dependencyCheck=true)
public class UserResultRequest {

    @Autowired DifficultyService difficultyService;
    @Autowired GameVersionService gameVersionService;
    @Autowired TopicService topicService;
    @Autowired Diff_VersionService diff_VersionService;

    private int score;
    @Min(value = 1, groups = ValidationStep1.class)
    @Max(value = QuestionListRequest.QUESTION_MAX_POOL_SIZE, groups = ValidationStep1.class)
    private int questionPoolSize;
    private int difficulty;
    private int topic;
    private int gameVersion;

    @AssertTrue(message = "Invalid difficulty", groups = ValidationStep1.class)
    public boolean isValidDifficulty() {

        return difficultyService.isValidDifficulty(difficulty);
    }

    @AssertTrue(message = "Invalid game version", groups = ValidationStep1.class)
    public boolean isValidGameVersion() {

        return gameVersionService.isValidGameVersion(gameVersion);
    }

    @AssertTrue(message = "Invalid topic", groups = ValidationStep1.class)
    public boolean isValidTopic() {

        return topicService.isValidTopic(topic);
    }

    @AssertTrue(message = "Invalid score", groups = ValidationStep2.class)
    public boolean isValidScore() {

        int maxPossibleScore = questionPoolSize * diff_VersionService.fetchScore(difficulty, gameVersion).get().getScorePerQuest();

        // check if within valid range
        return ((this.score >= 0) && (this.score <= maxPossibleScore));
    }

    public int getScore() {
        return score;
    }

    public int getQuestionPoolSize() {
        return questionPoolSize;
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

    public void setScore(int score) {
        this.score = score;
    }

    public void setQuestionPoolSize(int questionPoolSize) {
        this.questionPoolSize = questionPoolSize;
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
