package fictional.quizfinal.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserRequest {

    @JsonIgnore
    public static final int USERNAME_MAX_LEN = 12;

    @NotBlank(message = "missing username")
    @Size(max = USERNAME_MAX_LEN, message = "username '${validatedValue}' exceeds max length ({max})")
    @Pattern(regexp = "[^\\s]+", message = "username '${validatedValue}' contains whitespaces")
    private String username;

    public UserRequest(
            @NotBlank(message = "missing username") @Size(max = 12, message = "username '${validatedValue}' exceeds max length ({max})") @Pattern(regexp = "[^\\s]+", message = "username '${validatedValue}' contains whitespaces") String username) {
        this.username = username;
    }

    public UserRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}