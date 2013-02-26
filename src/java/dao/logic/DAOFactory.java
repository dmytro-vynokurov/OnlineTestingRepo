/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.logic;

import dao.*;
import dictionaries.BeanType;
import propertiesmanagement.globalvariables.VariablesManager;
/**
 *
 * @author Винокуров
 */
public class DAOFactory {
    private static IDBDAO getDBDao()throws UnsupportedOperationException{
         switch(VariablesManager.getInstance().getProperty("DB_CHOSEN")){
            case "MYSQL":
                return new MySQLDAO();
            default:
                throw new UnsupportedOperationException("Only MySQL supported");
        }
    }
    
    public static AnswerDAO getAnswerDAO(){
        IDBDAO dbDAO=getDBDao();
        return dbDAO.getAnswerDAO();
    }
    
    public static QuestionDAO getQuestionDAO(){
        IDBDAO dbDAO=getDBDao();
        return dbDAO.getQuestionDAO();
    }
    
    public static StudentDAO getStudentDAO(){
        IDBDAO dbDAO=getDBDao();
        return dbDAO.getStudentDAO();
    }
    
    public static SubjectDAO getSubjectDAO(){
        IDBDAO dbDAO=getDBDao();
        return dbDAO.getSubjectDAO();
    }
    
    public static TestDAO getTestDAO(){
        IDBDAO dbDAO=getDBDao();
        return dbDAO.getTestDAO();
    }
    
    public static TutorDAO getTutorDAO(){
        IDBDAO dbDAO=getDBDao();
        return dbDAO.getTutorDAO();
    }
    
}
