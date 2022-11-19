package fictional.quizfinal.payload.request;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fictional.quizfinal.entity.Topic;
import fictional.quizfinal.service.Diff_VersionService;
import fictional.quizfinal.service.DifficultyService;
import fictional.quizfinal.service.GameVersionService;
import fictional.quizfinal.service.QuestionService;
import fictional.quizfinal.service.TopicService;
import fictional.quizfinal.utility.validation.ValidationStep1;
import fictional.quizfinal.utility.validation.ValidationStep2;
import fictional.quizfinal.utility.validation.ValidationStep3;

@Configurable (dependencyCheck=true)
public class UserResultRequest {

    @Autowired DifficultyService difficultyService;
    @Autowired GameVersionService gameVersionService;
    @Autowired QuestionService questionService;
    @Autowired TopicService topicService;
    @Autowired Diff_VersionService diff_VersionService;

    @JsonIgnore
    private Topic topicObj;

    @NotNull
    @Min(value = 0)
    private Integer score;
    @NotNull
    @Min(value = 1)
    private Integer questionPoolSize;
    @NotNull
    private Integer difficulty;
    @NotNull
    private Integer topic;
    @NotNull
    private Integer gameVersion;

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

        topicObj = topicService.fetchTopic(topic).orElse(null);
        return topicObj != null;
    }

    @AssertTrue(message = "Question pool too large", groups = ValidationStep2.class)
    public boolean isValidQuestionPoolSize() {

        return questionPoolSize <= questionService.totalQuestions(topicObj);
    }

    @AssertTrue(message = "Invalid score", groups = ValidationStep3.class)
    public boolean isValidScore() {

        int maxPossibleScore = questionPoolSize * diff_VersionService.fetchScore(difficulty, gameVersion).get().getScorePerQuest();

        // check if within valid range
        return this.score <= maxPossibleScore;
    }

    public Topic getTopicObj() {
        return topicObj;
    }

    public void setTopicObj(Topic topicObj) {
        this.topicObj = topicObj;
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
