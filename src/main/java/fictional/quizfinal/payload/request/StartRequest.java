package fictional.quizfinal.payload.request;

import javax.validation.constraints.AssertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import fictional.quizfinal.service.DifficultyService;
import fictional.quizfinal.service.TopicService;

@Configurable (dependencyCheck=true)
public class StartRequest {

    @Autowired DifficultyService difficultyService;
    @Autowired TopicService topicService;

    private int diff;
    private int topic;
    
    @AssertTrue(message = "Invalid Difficulty")
    public boolean isValidDifficulty() {

        return difficultyService.isValidDifficulty(diff);
    }

    @AssertTrue(message = "Invalid Topic")
    public boolean isValidTopic() {

        return topicService.isValidTopic(topic);
    }

    public StartRequest(int diff, int topic) {

        System.out.println(difficultyService);

        this.diff = diff;
        this.topic = topic;
    }

    public StartRequest() {
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public int getTopic() {
        return topic;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }
}
