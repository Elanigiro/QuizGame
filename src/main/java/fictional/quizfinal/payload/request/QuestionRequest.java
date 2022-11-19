package fictional.quizfinal.payload.request;

import javax.validation.constraints.NotNull;

public class QuestionRequest {

    @NotNull
    private Integer questId;

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