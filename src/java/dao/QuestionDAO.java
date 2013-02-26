/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Question;
import beans.Test;
import dao.logic.AbstractDAO;
import java.util.List;

/**
 *
 * @author Винокуров
 */
public abstract class QuestionDAO  extends AbstractDAO{
    public abstract List<Question> getQuestions(int testID);
    public abstract Question getQuestion(int questionID);
    public abstract void addQuestion(int testID,String text,boolean oneAnswer);
    public abstract void updateQuestion(int questionID,int newTestID,
            String newText,boolean newOneAnswer);
    
    public List<Question> getQuestions(Test test){
        return getQuestions(test.getIDTest());
    }
    
    public void addQuestion(Test test,String text,boolean oneAnswer){
        addQuestion(test.getIDTest(),text,oneAnswer);
    }
    
    public void updateQuestion(Question question){
        updateQuestion(question.getIDQuestion(),question.getIDTest(),
                question.getText(),question.getOneAnswer());
    }
    
}
