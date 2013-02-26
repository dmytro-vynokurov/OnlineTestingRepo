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
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer iDStudent;
    private String firstName;
    private String secondName;
    private String middleName;
    private String login;
    private String password;

    public Student() {
    }

    public Student(Integer iDStudent) {
        this.iDStudent = iDStudent;
    }

    public Integer getIDStudent() {
        return iDStudent;
    }

    public void setIDStudent(Integer iDStudent) {
        this.iDStudent = iDStudent;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if (!(this.iDStudent.equals(other.iDStudent))) return false;
        if (!(this.firstName.equals(other.firstName))) return false;
        if (!(this.secondName.equals(other.secondName))) return false;
        if (!(this.middleName.equals(other.middleName))) return false;
        if (!(this.login.equals(other.login))) return false;
        if (!(this.password.equals(other.password))) return false;
        return true;
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.iDStudent);
        hash = 13 * hash + Objects.hashCode(this.firstName);
        hash = 17 * hash + Objects.hashCode(this.secondName);
        hash = 19 * hash + Objects.hashCode(this.middleName);
        hash = 7 * hash + Objects.hashCode(this.login);
        hash = 5 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public String toString() {
        return "Student #" + iDStudent + " " + secondName + " " + firstName + " " + 
                middleName + " " + login + " " + password;
    }
}
