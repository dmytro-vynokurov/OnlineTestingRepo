/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.logic;

import dao.*;
import dao.mysql.*;

/**
 *
 * @author Винокуров
 */
public class MySQLDAO implements IDBDAO {

    @Override
    public AnswerDAO getAnswerDAO() {
        return new MySQLAnswerDAO();
    }

    @Override
    public QuestionDAO getQuestionDAO() {
        return new MySQLQuestionDAO();
    }

    @Override
    public StudentDAO getStudentDAO() {
        return new MySQLStudentDAO();
    }

    @Override
    public SubjectDAO getSubjectDAO() {
        return new MySQLSubjectDAO();
    }

    @Override
    public TestDAO getTestDAO() {
        return new MySQLTestDAO();
    }

    @Override
    public TutorDAO getTutorDAO() {
        return new MySQLTutorDAO();
    }
}
