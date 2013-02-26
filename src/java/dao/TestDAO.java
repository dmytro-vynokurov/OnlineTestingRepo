/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dictionaries.BeanType;
import beans.Student;
import beans.Subject;
import beans.Test;
import beans.Tutor;
import dao.logic.AbstractDAO;
import java.util.List;

/**
 *
 * @author Винокуров
 */
public abstract class TestDAO extends AbstractDAO{
    public abstract List<Test> getTests();
    public abstract List<Test> getTests(BeanType beanType,int beanID);
    public abstract Test getTest(int testID);
    public abstract void addTest(int tutorID,int subjectID);
    public abstract void markReady(int testID);
    
    public List<Test> getTests(Subject subject){
        return getTests(BeanType.SUBJECT,subject.getIDSubject());
    }
    public List<Test> getTests(Tutor tutor){
        return getTests(BeanType.TUTOR,tutor.getIDTutor());
    }
    public List<Test> getTests(Student student){
        return getTests(BeanType.STUDENT,student.getIDStudent());
    }
    public void addTest(Tutor tutor,Subject subject){
        addTest(tutor.getIDTutor(),subject.getIDSubject());
    }
    public void markReady(Test test){
        markReady(test.getIDTest());
    }
    
    
}
