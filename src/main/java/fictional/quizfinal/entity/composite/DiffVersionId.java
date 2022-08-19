package fictional.quizfinal.entity.composite;

import java.io.Serializable;

public class DiffVersionId implements Serializable{

    private int idDiff;
    private int idVersion;

    public DiffVersionId(int idDiff, int idVersion) {
        this.idDiff = idDiff;
        this.idVersion = idVersion;
    }

    public DiffVersionId() {
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
        DiffVersionId other = (DiffVersionId) obj;
        if (idDiff != other.idDiff)
            return false;
        if (idVersion != other.idVersion)
            return false;
        return true;
    }
}
