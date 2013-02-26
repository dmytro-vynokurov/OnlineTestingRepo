/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.logic;

import dao.*;


/**
 *
 * @author Винокуров
 */
public interface IDBDAO {
    public AnswerDAO getAnswerDAO();
    public QuestionDAO getQuestionDAO();
    public StudentDAO getStudentDAO();
    public SubjectDAO getSubjectDAO();
    public TestDAO getTestDAO();
    public TutorDAO getTutorDAO();
    
}
