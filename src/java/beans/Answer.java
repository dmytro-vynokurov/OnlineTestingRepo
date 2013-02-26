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
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer iDAnswer;
    private String text;
    private Boolean correct;
    private Integer iDQuestion;

    public Answer() {
    }

    public Answer(Integer iDAnswer) {
        this.iDAnswer = iDAnswer;
    }

    public Integer getIDAnswer() {
        return iDAnswer;
    }

    public void setIDAnswer(Integer iDAnswer) {
        this.iDAnswer = iDAnswer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Integer getIDQuestion() {
        return iDQuestion;
    }

    public void setIDQuestion(Integer iDQuestion) {
        this.iDQuestion = iDQuestion;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if(!(this.iDAnswer.equals(other.iDAnswer)))return false;
        if(!(this.iDQuestion.equals(other.iDQuestion)))return false;
        if(!(this.correct.equals(other.correct)))return false;
        if(!(this.text.equals(other.text)))return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.iDAnswer);
        hash = 17 * hash + iDQuestion.hashCode();
        hash = 23 * hash + Objects.hashCode(this.text);
        return hash;
    }

    @Override
    public String toString() {
        return "Question #" + iDQuestion + " Answer #" + iDAnswer+ isCorrect() + " " + text;
    }
    
    private String isCorrect(){
        if(correct)return "Correct";
        else return "Wrong";
    }
}
