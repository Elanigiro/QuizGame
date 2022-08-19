package fictional.quizfinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fictional.quizfinal.entity.Question;
import fictional.quizfinal.entity.Topic;
import fictional.quizfinal.repository.QuestionRepository;

@Service
public class QuestionService {
    
    @Autowired QuestionRepository questionRepository;

    public Optional<Question> fetchQuestion(int id) {

        return questionRepository.findById(id);
    }

    public List<Question> fetchQuestions(Topic t) {

        return questionRepository.findByTopic(t);
    }

    public List<Integer> fetchQuestionCodes(Topic t) {

        List<Question> ls = fetchQuestions(t);
        List<Integer> codes = new ArrayList<Integer>();

        for (Question question : ls) {
            
            codes.add(question.getIdQuest());
        }

        return codes;
    }
}
