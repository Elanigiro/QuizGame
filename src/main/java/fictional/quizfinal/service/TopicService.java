package fictional.quizfinal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fictional.quizfinal.entity.Topic;
import fictional.quizfinal.repository.TopicRepository;

@Service
public class TopicService {
    
    @Autowired TopicRepository topicRepository;

    public Optional<Topic> fetchTopic(int id) {

        return topicRepository.findById(id);
    }

    public boolean isValidTopic(int id) {

        return fetchTopic(id).isPresent();
    }
}
