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
public class Tutor implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer iDTutor;
    private String firstName;
    private String secondName;
    private String middleName;
    private String login;
    private String password;

    public Tutor() {
    }

    public Tutor(Integer iDTutor) {
        this.iDTutor = iDTutor;
    }

    public Integer getIDTutor() {
        return iDTutor;
    }

    public void setIDTutor(Integer iDTutor) {
        this.iDTutor = iDTutor;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof Tutor)) {
            return false;
        }
        Tutor other = (Tutor) object;
        if (!(this.iDTutor.equals(other.iDTutor))) return false;
        if (!(this.firstName.equals(other.firstName))) return false;
        if (!(this.secondName.equals(other.secondName))) return false;
        if (!(this.middleName.equals(other.middleName))) return false;
        if (!(this.login.equals(other.login))) return false;
        if (!(this.password.equals(other.password))) return false;
        return true;
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.iDTutor);
        hash = 23 * hash + Objects.hashCode(this.firstName);
        hash = 17 * hash + Objects.hashCode(this.secondName);
        hash = 19 * hash + Objects.hashCode(this.middleName);
        hash = 31 * hash + Objects.hashCode(this.login);
        hash = 5 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public String toString() {
        return "Tutor #" + iDTutor + " " + secondName + " " + firstName + " " + 
                middleName + " " + login + " " + password;
    }
}
