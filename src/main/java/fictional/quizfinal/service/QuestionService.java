package fictional.quizfinal.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fictional.quizfinal.entity.Question;
import fictional.quizfinal.entity.Topic;
import fictional.quizfinal.repository.QuestionRepository;

@Service
@Transactional
public class QuestionService {
    
    @Autowired QuestionRepository questionRepository;

    public Optional<Question> fetchQuestion(int id) {

        return questionRepository.myEagerFindById(id);
    }

    public List<Question> fetchQuestions(Topic t, PageRequest pr) {

        return questionRepository.findByTopic(t, pr);
    }

    public long totalQuestions(Topic t) {

        return questionRepository.countByTopic(t);
    }

    public List<Integer> fetchQuestionCodes(Topic t, int howMany, boolean random) {

        List<Question> ls = new ArrayList<>();

        if (random) {

            long max = totalQuestions(t);

            Set<Long> randomPages = new LinkedHashSet<>();
            do {

                randomPages.add(ThreadLocalRandom.current().nextLong(max));
            } while(randomPages.size() < howMany);

            for (Long page : randomPages) {
                
                //it shouldn't overflow unless we have a very large DB
                ls.add(fetchQuestions(t, PageRequest.of(Math.toIntExact(page), 1)).get(0));
            }
        }
        else {

            ls = fetchQuestions(t, PageRequest.of(0, howMany));
        }

        List<Integer> codes = new ArrayList<Integer>();

        for (Question question : ls) {
            
            codes.add(question.getIdQuest());
        }

        return codes;
    }
}
