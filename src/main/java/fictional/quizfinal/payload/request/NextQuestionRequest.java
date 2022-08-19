package fictional.quizfinal.payload.request;

import javax.validation.constraints.AssertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import fictional.quizfinal.service.DifficultyService;
import fictional.quizfinal.service.QuestionService;

@Configurable (dependencyCheck=true)
public class NextQuestionRequest {

    @Autowired QuestionService questionService;
    @Autowired DifficultyService difficultyService;

    private int questId;
    private int diff;

    @AssertTrue(message = "Invalid Question Id")
    public boolean isValidQuestId() {

        return questionService.fetchQuestion(questId).isPresent();
    }

    @AssertTrue(message = "Invalid Difficulty")
    public boolean isValidDifficulty() {

        return difficultyService.isValidDifficulty(diff);
    }

    public NextQuestionRequest(int questId, int diff) {
        this.questId = questId;
        this.diff = diff;
    }

    public NextQuestionRequest() {
    }

    public int getQuestId() {
        return questId;
    }

    public void setQuestId(int questId) {
        this.questId = questId;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }
}
