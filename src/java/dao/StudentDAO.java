/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Answer;
import dictionaries.BeanType;
import beans.Student;
import beans.Subject;
import beans.Test;
import dao.logic.AbstractDAO;
import java.util.List;

/**
 *
 * @author Винокуров
 */
public abstract class StudentDAO extends AbstractDAO{
    public abstract Student getStudent(int studentID);
    public abstract Student getStudent(String login);
    public abstract List<Student> getStudents();
    public abstract List<Student> getStudents(BeanType beanType,int beanID);
    public abstract void addStudent(String firstName,String lastName,String middleName,
            String login,String password);
    public abstract void updateStudent(int studentID,String newFirstName,
            String newSeondName,String newMiddleName,
            String newLogin,String newPassword);
    public abstract Student checkLogin(String login,String password);
    public abstract void addAnswer(int studentID,int answerID);
    public abstract void removeAnswer(int studentID,int answerID);
    public abstract void alterAnswer(int studentID,int answerID);
    
    
    public List<Student> getStudents(Subject subject){
        return getStudents(BeanType.SUBJECT,subject.getIDSubject());
    }
    
    public List<Student> getStudents(Test test){
        return getStudents(BeanType.TEST,test.getIDTest());
    }
    
    public void updateStudent(Student student){
        updateStudent(student.getIDStudent(),student.getFirstName(),
                student.getSecondName(),student.getMiddleName(),
                student.getLogin(),student.getPassword());
    }
    
    public void addAnswer(Student student,Answer answer){
        addAnswer(student.getIDStudent(),answer.getIDAnswer());
    }
    
    public void removeAnswer(Student student,Answer answer){
        removeAnswer(student.getIDStudent(),answer.getIDAnswer());
    }
    
    public void alterAnswer(Student student,Answer answer){
        alterAnswer(student.getIDStudent(),answer.getIDAnswer());
    }
    
}
