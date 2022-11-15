package fictional.quizfinal.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fictional.quizfinal.dto.ILeaderboardEntry;
import fictional.quizfinal.entity.DiffVersion;
import fictional.quizfinal.entity.Question;
import fictional.quizfinal.entity.QuizUser;
import fictional.quizfinal.entity.UserScore;
import fictional.quizfinal.payload.request.QuestionListRequest;
import fictional.quizfinal.payload.request.QuestionRequest;
import fictional.quizfinal.payload.request.ScoreRequest;
import fictional.quizfinal.payload.request.UserIdRequest;
import fictional.quizfinal.payload.request.UserRequest;
import fictional.quizfinal.payload.request.UserResultRequest;
import fictional.quizfinal.payload.response.QuestionListResponse;
import fictional.quizfinal.service.AnswerService;
import fictional.quizfinal.service.Diff_VersionService;
import fictional.quizfinal.service.DifficultyService;
import fictional.quizfinal.service.GameVersionService;
import fictional.quizfinal.service.QuestionService;
import fictional.quizfinal.service.QuizUserService;
import fictional.quizfinal.service.TopicService;
import fictional.quizfinal.service.UserScoreService;
import fictional.quizfinal.utility.Logging;
import fictional.quizfinal.utility.validation.ValidationOrder;

@RestController
@RequestMapping("")
public class ServerSideRESTController {

    @Autowired QuestionService questionService;
    @Autowired TopicService topicService;
    @Autowired Diff_VersionService diff_VersionService;
    @Autowired GameVersionService gameVersionService;
    @Autowired AnswerService answerService;
    @Autowired QuizUserService quizUserService;
    @Autowired DifficultyService difficultyService;
    @Autowired UserScoreService userScoreService;

    // Sends question list and game version as JSON
    @GetMapping(path = "/quiz/questions/topic/{topic}", params = {"limit", "random"}) 
    public ResponseEntity<QuestionListResponse> getQuestionList(@Valid QuestionListRequest qlr) {

        QuestionListResponse res = new QuestionListResponse();

        // fetch the question ids (random or not)
        res.setQuestionCodes(questionService.fetchQuestionCodes(topicService.fetchTopic(qlr.getTopic()).get(), qlr.getLimit(), qlr.isRandom()));
        // current game version
        res.setGameVersion(gameVersionService.fetchLatestVersionId().get());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Vary", "*"); // uncacheable resource
        return new ResponseEntity<QuestionListResponse>(res, responseHeaders, HttpStatus.OK);
    }

    // Sends question as JSON
    @GetMapping("/quiz/questions/{questId}")
    public ResponseEntity<Question> getQuestion(@Valid QuestionRequest qr) {

        Question res = questionService.fetchQuestion(qr.getQuestId()).get();

        return new ResponseEntity<Question>(res, HttpStatus.OK);
    }

    // Sends score per question as JSON
    @GetMapping("/quiz/scores/{version}/{difficulty}")
    public ResponseEntity<DiffVersion> getDiffVersionScore(@Validated(ValidationOrder.class) ScoreRequest sr) {

        DiffVersion res = diff_VersionService.fetchScore(sr.getDifficulty(), sr.getVersion()).get();

        return new ResponseEntity<DiffVersion>(res, HttpStatus.OK);
    }

    // Sends user as JSON if found
    @GetMapping(path = "/users", params = {"username"})
    public ResponseEntity<QuizUser> getUserByUsername(@Valid UserRequest ur) {

        QuizUser res = quizUserService.fetchUser(ur.getUsername()).orElse(null);

        if (res == null) {

            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // Saves the quiz result into the DB if validated
    @PostMapping("/users/{userId}/scores")
    public ResponseEntity<Integer> postResult(@Valid UserIdRequest uir, @Validated(ValidationOrder.class) @RequestBody UserResultRequest req) {

        UserScore us = new UserScore();

        QuizUser tmpQuizUser = quizUserService.fetchUserById(uir.getUserId()).get();

        us.setQuizuser(tmpQuizUser);
        us.setScoreTimestamp(Timestamp.from(Instant.now()));
        us.setDifficulty(difficultyService.fetchDifficulty(req.getDifficulty()).get());
        us.setTotalScore(req.getResult());
        us.setGameVersion(gameVersionService.fetchGameVersion(req.getGameVersion()).get());
        us.setTopic(topicService.fetchTopic(req.getTopic()).get());
        
        int newId = userScoreService.saveUserScore(us).getIdRow();

        Logging.logDebug(us.toString(), UserResultRequest.class.getName());

        return new ResponseEntity<Integer>(newId, HttpStatus.CREATED);
    }

    // Saves a new user into the DB
    @PostMapping("/users")
    public ResponseEntity<Integer> postUser(@Valid @RequestBody UserRequest usr) {

        QuizUser newUser = new QuizUser();
        newUser.setFirstLoginDate(Timestamp.from(Instant.now()));
        newUser.setNickname(usr.getUsername());

        int newId = quizUserService.saveUser(newUser).getIdUser();

        return new ResponseEntity<Integer>(newId, HttpStatus.CREATED);
    }

    // Sends the leaderboard as JSON
    @GetMapping(path = "/users")
    public ResponseEntity<String> getLed() {

        return new ResponseEntity<>("ciaone", HttpStatus.OK);
    }

    // Sends the leaderboard as JSON - note the value of the "leaderboard" parameter is actually meaningless
    @GetMapping(path = "/users", params = {"leaderboard"})
    public ResponseEntity<List<ILeaderboardEntry>> getLeaderboard() {

        List<ILeaderboardEntry> res = userScoreService.fetchLeaderboard(gameVersionService.fetchLatestVersionId().get());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Vary", "*"); // uncacheable resource
        return new ResponseEntity<List<ILeaderboardEntry>>(res, responseHeaders, HttpStatus.OK);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public Map<String, String> handleValidationExceptions(BindException ex) {

        Map<String, String> errors = new HashMap<>();

        for (FieldError err : ex.getFieldErrors()) {
                
            errors.put(err.getField(), err.getDefaultMessage());

            Logging.logError("Validation Failed: " + err.getDefaultMessage(), "InputValidation");
        }
        
        return errors;
    }
}
