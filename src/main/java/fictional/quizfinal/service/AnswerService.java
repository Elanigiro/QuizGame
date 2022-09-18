package fictional.quizfinal.service;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fictional.quizfinal.entity.Answer;
import fictional.quizfinal.entity.Question;
import fictional.quizfinal.repository.AnswerRepository;

@Service
@Transactional
public class AnswerService {
    
    @Autowired AnswerRepository answerRepository;

    public List<Answer> fetchAnswers(Question q) {

        return answerRepository.findByQuestion(q);
    }

    // select answers by difficulty (2 answers => EASY, 3 answers => MEDIUM, 4 answers => HARD)
    public void selectAnswersByDifficulty(List<Answer> allAnswers, int diff) {

        int quantity;
        switch (diff) {

            case 1:
                quantity = 2;
                break;

            case 2:
                quantity = 3;
                break;

            case 3:
                quantity = 4;
                break;

            default:
                quantity = allAnswers.size();
                break;
        }

        // remove wrong answers if necessary depending on the difficulty level
        ListIterator<Answer> it = allAnswers.listIterator();
        while((allAnswers.size() > quantity) && it.hasNext()) {
            
            if (it.next().isCorrect() == false) {

                it.remove();
            }
        }
    }
}
