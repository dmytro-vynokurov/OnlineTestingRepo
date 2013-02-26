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
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer iDTest;
    private Boolean ready;
    private Integer iDSubject;
    private Integer iDTutor;

    public Test() {
    }

    public Test(Integer iDTest) {
        this.iDTest = iDTest;
    }

    public Integer getIDTest() {
        return iDTest;
    }

    public void setIDTest(Integer iDTest) {
        this.iDTest = iDTest;
    }

    public Boolean getReady() {
        return ready;
    }

    public void setReady(Boolean ready) {
        this.ready = ready;
    }

    public Integer getIDSubject() {
        return iDSubject;
    }

    public void setIDSubject(Integer iDSubject) {
        this.iDSubject = iDSubject;
    }

    public Integer getIDTutor() {
        return iDTutor;
    }

    public void setIDTutor(Integer iDTutor) {
        this.iDTutor = iDTutor;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if(!(this.iDTest.equals(other.iDTest)))return false;
        if(!(this.iDTutor.equals(other.iDTutor)))return false;
        if(!(this.iDSubject.equals(other.iDSubject)))return false;
        if(!(this.ready.equals(other.ready)))return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.iDTest);
        hash = 17 * hash + Objects.hashCode(this.ready);
        hash = 19 * hash + Objects.hashCode(this.iDSubject);
        hash = 31 * hash + Objects.hashCode(this.iDTutor);
        return hash;
    }

    @Override
    public String toString() {
        return "Test #"+iDTest+" Subject #"+iDSubject+ " Tutor #"+iDTutor+" "+isReady();
    }
    
    private String isReady(){
        if(ready)return "Ready";
        else return "Not ready";
    }
}
