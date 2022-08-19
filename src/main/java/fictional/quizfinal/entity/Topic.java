package fictional.quizfinal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TOPIC")
public class Topic {

    @Id
    private int idTopic;
    @Column(nullable = false, length = 150)
    private String topic;

    public Topic(int idTopic, String topic) {
        this.idTopic = idTopic;
        this.topic = topic;
    }

    public Topic() {
    }

    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idTopic;
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
        Topic other = (Topic) obj;
        if (idTopic != other.idTopic)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Topic [idTopic=" + idTopic + ", topic=" + topic + "]";
    }
}
