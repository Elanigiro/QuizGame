package fictional.quizfinal.payload.request;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fictional.quizfinal.entity.Topic;
import fictional.quizfinal.service.QuestionService;
import fictional.quizfinal.service.TopicService;
import fictional.quizfinal.utility.validation.ValidationStep1;
import fictional.quizfinal.utility.validation.ValidationStep2;

@Configurable (dependencyCheck=true)
public class QuestionListRequest {

    @Autowired TopicService topicService;
    @Autowired QuestionService questionService;

    @JsonIgnore
    private Topic topicObj;

    @NotNull
    private Integer topic;
    @NotNull
    @Min(value = 1, message = "limit must be strictly greater than 0")
    private Integer limit;
    @NotNull
    private Boolean random;

    @AssertTrue(message = "Invalid Topic", groups = ValidationStep1.class)
    public boolean isValidTopic() {

        topicObj = topicService.fetchTopic(topic).orElse(null);
        return topicObj != null;
    }

    @AssertTrue(message = "Question pool too large", groups = ValidationStep2.class)
    public boolean isValidLimit() {

        return limit <= questionService.totalQuestions(topicObj);
    }

    public QuestionListRequest(int topic, int limit, boolean random) {

        this.topic = topic;
        this.limit = limit;
        this.random = random;
    }

    public QuestionListRequest() {
    }

    public Topic getTopicObj() {
        return topicObj;
    }

    public void setTopicObj(Topic topicObj) {
        this.topicObj = topicObj;
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
