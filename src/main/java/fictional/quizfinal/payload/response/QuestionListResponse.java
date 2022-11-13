package fictional.quizfinal.payload.response;

import java.util.List;

public class QuestionListResponse {

    public static final int QUESTION_MAX_POOL_SIZE = 35;

    private int gameVersion;
    private List<Integer> questionCodes;

    public QuestionListResponse(int gameVersion, List<Integer> questionCodes) {
        this.gameVersion = gameVersion;
        this.questionCodes = questionCodes;
    }

    public QuestionListResponse() {
    }

    public int getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(int gameVersion) {
        this.gameVersion = gameVersion;
    }

    public List<Integer> getQuestionCodes() {
        return questionCodes;
    }

    public void setQuestionCodes(List<Integer> questionCodes) {
        this.questionCodes = questionCodes;
    }
}