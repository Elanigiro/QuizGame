package fictional.quizfinal.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fictional.quizfinal.dto.ILeaderboardEntry;
import fictional.quizfinal.service.GameVersionService;
import fictional.quizfinal.service.UserScoreService;
import fictional.quizfinal.utility.Logging;

@RestController
public class HomeController {

    @Autowired GameVersionService gameVersionService;
    @Autowired UserScoreService userScoreService;

    @GetMapping({"/", "/home"})
    public ModelAndView home() {

        return new ModelAndView("/home/index");
    }

    // Sends the response as JSON
    @GetMapping("/home/leaderboard")
    public ResponseEntity<List<ILeaderboardEntry>> getLeaderboard() {

        List<ILeaderboardEntry> res = userScoreService.fetchLeaderboard(gameVersionService.fetchLatestVersionId().get());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Vary", "*"); // uncacheable resource
        return new ResponseEntity<List<ILeaderboardEntry>>(res, responseHeaders, HttpStatus.OK);
    }

    // If DB is a not in a valid state (eg. The Leaderboard View is missing entirely)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoSuchElementException.class)
    public String handleDBExceptions(NoSuchElementException ex) {
        
        Logging.logError(ex.getMessage(), Exception.class.getName());
        return "Internal DB Error";
    }
}
