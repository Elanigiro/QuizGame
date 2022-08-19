package fictional.quizfinal.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fictional.quizfinal.entity.Question;
import fictional.quizfinal.entity.QuizUser;
import fictional.quizfinal.entity.UserScore;
import fictional.quizfinal.payload.request.NextQuestionRequest;
import fictional.quizfinal.payload.request.StartRequest;
import fictional.quizfinal.payload.request.UserScoreRequest;
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
@RequestMapping("/quiz")
public class QuizController {

    @Autowired QuestionService questionService;
    @Autowired TopicService topicService;
    @Autowired Diff_VersionService diff_VersionService;
    @Autowired GameVersionService gameVersionService;
    @Autowired AnswerService answerService;
    @Autowired QuizUserService quizUserService;
    @Autowired DifficultyService difficultyService;
    @Autowired UserScoreService userScoreService;

    // Sends question list as JSON
    @GetMapping("/start") 
    public ResponseEntity<QuestionListResponse> startQuiz(@Valid StartRequest start) {

        QuestionListResponse res = new QuestionListResponse();
        res.setQuestionCodes(questionService.fetchQuestionCodes(topicService.fetchTopic(start.getTopic()).get()));

        // random order
        Collections.shuffle(res.getQuestionCodes());
        // select only a POOL of N Questions
        res.setQuestionCodes(res.getQuestionCodes().subList(0, QuestionListResponse.QUESTION_POOL_SIZE));
        // current game version
        res.setGameVersion(gameVersionService.fetchLatestVersionId().get());
        // score per correct Answer for the chosen difficulty and current game version
        res.setScorePerQuestion(diff_VersionService.fetchScore(start.getDiff(), res.getGameVersion()));

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Vary", "*"); // uncacheable resource
        return new ResponseEntity<QuestionListResponse>(res, responseHeaders, HttpStatus.OK);
    }

    // Sends question as JSON
    @GetMapping("/nextQuestion")
    public ResponseEntity<Question> nextQuestion(@Valid NextQuestionRequest nx) {

        Question res = questionService.fetchQuestion(nx.getQuestId()).get();

        Collections.shuffle(res.getAnswers()); // it ensures random selection of wrong answers
        answerService.selectAnswersByDifficulty(res.getAnswers(), nx.getDiff());
        Collections.shuffle(res.getAnswers());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Vary", "*"); // uncacheable resource
        return new ResponseEntity<Question>(res, responseHeaders, HttpStatus.OK);
    }

    // Saves the quiz result into the DB if validated
    @PostMapping("/results")
    public ResponseEntity<String> saveResults(@Validated(ValidationOrder.class) @RequestBody UserScoreRequest req) {

        UserScore us = new UserScore();

        Optional<QuizUser> tmpQuizUser = quizUserService.fetchUser(req.getUsername());

        // new user
        if (tmpQuizUser.isEmpty()) {

            us.setQuizuser(new QuizUser());
            us.getQuizuser().setFirstLoginDate(Timestamp.from(Instant.now()));
            us.getQuizuser().setNickname(req.getUsername());

            quizUserService.saveUser(us.getQuizuser());
        }
        else {

            us.setQuizuser(tmpQuizUser.get());
        }

        us.setScoreTimestamp(Timestamp.from(Instant.now()));
        us.setDifficulty(difficultyService.fetchDifficulty(req.getDifficulty()).get());
        us.setTotalScore(req.getResult());
        us.setGameVersion(gameVersionService.fetchGameVersion(req.getGameVersion()).get());
        us.setTopic(topicService.fetchTopic(req.getTopic()).get());
        
        userScoreService.saveUserScore(us);

        Logging.logDebug(us.toString(), UserScoreRequest.class.getName());

        return new ResponseEntity<String>("Result saved", HttpStatus.CREATED);
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
