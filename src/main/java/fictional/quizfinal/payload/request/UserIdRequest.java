package fictional.quizfinal.payload.request;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import fictional.quizfinal.service.QuizUserService;
import fictional.quizfinal.utility.validation.ValidationStep1;

@Configurable (dependencyCheck=true)
public class UserIdRequest {

    @Autowired QuizUserService quizUserService;

    @NotNull
    private Integer userId;

    @AssertTrue(message = "Invalid user id", groups = ValidationStep1.class)
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
