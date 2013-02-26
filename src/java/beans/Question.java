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
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer iDQuestion;
    private String text;
    private Boolean oneAnswer;
    private Integer iDTest;

    public Question() {
    }

    public Question(Integer iDQuestion) {
        this.iDQuestion = iDQuestion;
    }

    public Integer getIDQuestion() {
        return iDQuestion;
    }

    public void setIDQuestion(Integer iDQuestion) {
        this.iDQuestion = iDQuestion;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getOneAnswer() {
        return oneAnswer;
    }

    public void setOneAnswer(Boolean oneAnswer) {
        this.oneAnswer = oneAnswer;
    }

    public Integer getIDTest() {
        return iDTest;
    }

    public void setIDTest(Integer iDTest) {
        this.iDTest = iDTest;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if(!(this.iDQuestion.equals(other.iDQuestion)))return false;
        if(!(this.iDTest.equals(other.iDTest)))return false;
        if(!(this.oneAnswer.equals(other.oneAnswer)))return false;
        if(!(this.text.equals(other.text)))return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.iDQuestion);
        hash = 23 * hash + iDTest.hashCode();
        hash = 17 * hash + Objects.hashCode(this.text);
        hash = 13 * hash + Objects.hashCode(this.oneAnswer);
        return hash;
    }

    @Override
    public String toString() {
        return "Test #" + iDTest + " Question #" + iDQuestion + " " +  
                "has 1 answer: "+ oneAnswer+ " "+ text;
    }
}
