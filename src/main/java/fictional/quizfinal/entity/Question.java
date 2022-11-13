package fictional.quizfinal.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "QUESTION")
public class Question {
    @Id
    private int idQuest;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "topic")
    private Topic topic;
    @Column(length = 250, nullable = false)
    private String question;
    @Column(length = 100, nullable = false)
    private String source;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<Answer> answers;
    
    public Question(int idQuest, Topic topic, String question, String source, List<Answer> answers) {
        this.idQuest = idQuest;
        this.topic = topic;
        this.question = question;
        this.source = source;
        this.answers = answers;
    }

    public Question() {
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
    
    public int getIdQuest() {
        return idQuest;
    }

    public void setIdQuest(int idQuest) {
        this.idQuest = idQuest;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idQuest;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Question other = (Question) obj;
        if (idQuest != other.idQuest)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Question [idQuest=" + idQuest + ", question=" + question + ", source=" + source + ", topic=" + topic
                + "]";
    }
}
