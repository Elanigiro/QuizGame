package fictional.quizfinal.payload.request;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fictional.quizfinal.service.TopicService;

@Configurable (dependencyCheck=true)
public class QuestionListRequest {

    @Autowired TopicService topicService;

    @JsonIgnore
    public static final int QUESTION_MAX_POOL_SIZE = 35;

    private int topic;
    @Min(value = 1, message = "limit must be strictly greater than 0")
    @Max(value = QUESTION_MAX_POOL_SIZE, message = "limit must be less than " + QUESTION_MAX_POOL_SIZE)
    private int limit;
    private boolean random;

    @AssertTrue(message = "Invalid Topic")
    public boolean isValidTopic() {

        return topicService.isValidTopic(topic);
    }

    public QuestionListRequest(int topic, int limit, boolean random) {

        this.topic = topic;
        this.limit = limit;
        this.random = random;
    }

    public QuestionListRequest() {
    }

    public int getTopic() {
        return topic;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }
}