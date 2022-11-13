package fictional.quizfinal.payload.request;

import javax.validation.constraints.AssertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import fictional.quizfinal.service.QuestionService;

@Configurable (dependencyCheck=true)
public class QuestionRequest {

    @Autowired QuestionService questionService;

    private int questId;

    @AssertTrue(message = "Invalid Question Id")
    public boolean isValidQuestId() {

        return questionService.fetchQuestion(questId).isPresent();
    }

    public QuestionRequest(int questId) {

        this.questId = questId;
    }

    public QuestionRequest() {
    }

    public int getQuestId() {
        return questId;
    }

    public void setQuestId(int questId) {
        this.questId = questId;
    }
}