/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Answer;
import dictionaries.BeanType;
import beans.Question;
import beans.Student;
import dao.logic.AbstractDAO;
import java.util.List;

/**
 *
 * @author Винокуров
 */
public abstract class AnswerDAO extends AbstractDAO{
    public abstract Answer getAnswer(int answerID)throws Exception ;
    public abstract List<Answer> getAnswers(BeanType beanType,int questionID);
    public abstract void addAnswer(int questionID,String text,boolean isCorrect);
    public abstract void updateAnswer(int answerID,int newQuestionID,
            String newText,boolean newCorrect);
    
    public List<Answer> getAnswers(Question question){
        return getAnswers(BeanType.QUESTION,question.getIDQuestion());
    }
    
    public List<Answer> getAnswers(Student student){
        return getAnswers(BeanType.STUDENT,student.getIDStudent());
    }
    
    public void addAnswer(Question question,String text,boolean isCorrect){
        addAnswer(question.getIDQuestion(),text,isCorrect);
    }
    
    public void addTrueFalse(int questionID,boolean trueIsCorrect){
        addAnswer(questionID, "True", trueIsCorrect);
        addAnswer(questionID, "False", !trueIsCorrect);                
    }
    
    public void addTrueFalse(Question question,boolean trueIsCorrect){
        addTrueFalse(question.getIDQuestion(),trueIsCorrect);
    }
    
    public void updateAnswer(Answer answer){
        updateAnswer(answer.getIDAnswer(),answer.getIDQuestion(),
                answer.getText(),answer.getCorrect());
    }
    
}
