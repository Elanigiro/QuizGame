package fictional.quizfinal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DIFFICULTY")
public class Difficulty {
    @Id
    private int idDiff;
    @Column(length = 50, nullable = false)
    private String diff;

    public Difficulty(int idDiff, String diff) {
        this.idDiff = idDiff;
        this.diff = diff;
    }

    public Difficulty() {
    }

    public int getId_diff() {
        return idDiff;
    }

    public void setId_diff(int idDiff) {
        this.idDiff = idDiff;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idDiff;
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
        Difficulty other = (Difficulty) obj;
        if (idDiff != other.idDiff)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Difficulty [diff=" + diff + ", idDiff=" + idDiff + "]";
    }
}
