package fictional.quizfinal.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GAMEVERSION")
public class GameVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVersion;
    @Column(columnDefinition = "TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP")
    private Timestamp launchDate;

    public GameVersion() {
    }

    public int getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(int idVersion) {
        this.idVersion = idVersion;
    }

    public Timestamp getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Timestamp launchDate) {
        this.launchDate = launchDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idVersion;
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
        GameVersion other = (GameVersion) obj;
        if (idVersion != other.idVersion)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GameVersion [idVersion=" + idVersion + ", launchDate=" + launchDate + "]";
    }
}
