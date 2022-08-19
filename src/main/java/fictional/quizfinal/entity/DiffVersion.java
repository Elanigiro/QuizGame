package fictional.quizfinal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import fictional.quizfinal.entity.composite.DiffVersionId;

@Entity
@Table(name = "DIFFVERSION")
@IdClass(DiffVersionId.class)
public class DiffVersion {

    @Id
    private int idDiff;
    @Id
    private int idVersion;
    @Column(nullable = false)
    private int scorePerQuest;

    public DiffVersion(int idDiff, int idVersion, int scorePerQuest) {
        this.idDiff = idDiff;
        this.idVersion = idVersion;
        this.scorePerQuest = scorePerQuest;
    }

    public DiffVersion() {
    }

    public int getIdDiff() {
        return idDiff;
    }

    public void setIdDiff(int idDiff) {
        this.idDiff = idDiff;
    }

    public int getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(int idVersion) {
        this.idVersion = idVersion;
    }

    public int getScorePerQuest() {
        return scorePerQuest;
    }

    public void setScorePerQuest(int scorePerQuest) {
        this.scorePerQuest = scorePerQuest;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idDiff;
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
        DiffVersion other = (DiffVersion) obj;
        if (idDiff != other.idDiff)
            return false;
        if (idVersion != other.idVersion)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Diff_Version [idDiff=" + idDiff + ", idVersion=" + idVersion + ", scorePerQuest="
                + scorePerQuest + "]";
    }

}
