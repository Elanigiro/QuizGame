package fictional.quizfinal.payload.request;

import javax.validation.constraints.AssertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import fictional.quizfinal.service.QuizUserService;

@Configurable (dependencyCheck=true)
public class UserIdRequest {

    @Autowired QuizUserService quizUserService;

    private int userId;

    @AssertTrue(message = "Invalid user id")
    public boolean isValidUserId() {

        return quizUserService.isValidUserId(userId);
    }

    public UserIdRequest() {
    }

    public UserIdRequest(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
