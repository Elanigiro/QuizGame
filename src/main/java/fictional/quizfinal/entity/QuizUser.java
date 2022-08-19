package fictional.quizfinal.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import fictional.quizfinal.payload.request.UserScoreRequest;

@Entity
@Table(name = "QUIZUSER")
public class QuizUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    @NaturalId
    @Column(nullable = false, unique = true, length = UserScoreRequest.USERNAME_MAX_LEN)
    private String nickname;
    @Column(columnDefinition = "TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP")
    private Timestamp firstLoginDate;

    public QuizUser(String nickname) {
        this.nickname = nickname;
    }

    public QuizUser() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Timestamp getFirstLoginDate() {
        return firstLoginDate;
    }

    public void setFirstLoginDate(Timestamp firstLoginDate) {
        this.firstLoginDate = firstLoginDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idUser;
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
        QuizUser other = (QuizUser) obj;
        if (idUser != other.idUser)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "QuizUser [firstLoginDate=" + firstLoginDate + ", idUser=" + idUser + ", nickname=" + nickname
                + "]";
    }
}
