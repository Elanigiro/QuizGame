package fictional.quizfinal.payload.response;

import java.util.List;

public class QuestionListResponse {

    public static final int QUESTION_POOL_SIZE = 10;

    private int scorePerQuestion;
    private int gameVersion;
    private List<Integer> questionCodes;

    public QuestionListResponse(int scorePerQuestion, int gameVersion, List<Integer> questionCodes) {
        this.scorePerQuestion = scorePerQuestion;
        this.gameVersion = gameVersion;
        this.questionCodes = questionCodes;
    }

    public QuestionListResponse() {
    }

    public static int getQuestionPoolSize() {
        return QUESTION_POOL_SIZE;
    }

    public int getScorePerQuestion() {
        return scorePerQuestion;
    }

    public void setScorePerQuestion(int scorePerQuestion) {
        this.scorePerQuestion = scorePerQuestion;
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