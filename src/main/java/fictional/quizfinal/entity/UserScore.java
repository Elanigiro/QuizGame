package fictional.quizfinal.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERSCORE")
public class UserScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRow;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "difficulty")
    private Difficulty difficulty;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "quizuser")
    private QuizUser quizuser;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "gameVersion")
    private GameVersion gameVersion;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "topic")
    private Topic topic;
    @Column(nullable = false)
    private Timestamp scoreTimestamp;
    @Column(nullable = false)
    private int totalScore;

    public UserScore(Difficulty difficulty, QuizUser quizuser, GameVersion gameVersion, Topic topic,
            Timestamp scoreTimestamp, int totalScore) {
        this.difficulty = difficulty;
        this.quizuser = quizuser;
        this.gameVersion = gameVersion;
        this.topic = topic;
        this.scoreTimestamp = scoreTimestamp;
        this.totalScore = totalScore;
    }

    public UserScore() {
    }

    public int getIdRow() {
        return idRow;
    }

    public void setIdRow(int idRow) {
        this.idRow = idRow;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public QuizUser getQuizuser() {
        return quizuser;
    }

    public void setQuizuser(QuizUser quizuser) {
        this.quizuser = quizuser;
    }

    public GameVersion getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(GameVersion gameVersion) {
        this.gameVersion = gameVersion;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Timestamp getScoreTimestamp() {
        return scoreTimestamp;
    }

    public void setScoreTimestamp(Timestamp scoreTimestamp) {
        this.scoreTimestamp = scoreTimestamp;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idRow;
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
        UserScore other = (UserScore) obj;
        if (idRow != other.idRow)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserScore [difficulty=" + difficulty + ", gameVersion=" + gameVersion + ", idRow=" + idRow
                + ", quizuser=" + quizuser + ", scoreTimestamp=" + scoreTimestamp + ", topic=" + topic
                + ", totalScore=" + totalScore + "]";
    }

}
