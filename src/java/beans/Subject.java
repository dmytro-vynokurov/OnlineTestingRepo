/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Vinokurov
 */
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer iDSubject;
    private String subjectName;

    public Subject() {
    }

    public Subject(Integer iDSubject) {
        this.iDSubject = iDSubject;
    }

    public Integer getIDSubject() {
        return iDSubject;
    }

    public void setIDSubject(Integer iDSubject) {
        this.iDSubject = iDSubject;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if(!(this.iDSubject.equals(other.iDSubject)))return false;
        if(!(this.subjectName.equals(other.subjectName)))return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.iDSubject);
        hash = 17 * hash + Objects.hashCode(this.subjectName);
        return hash;
    }

    @Override
    public String toString() {
        return "Subject #" + iDSubject + " " + subjectName;
    }
}
