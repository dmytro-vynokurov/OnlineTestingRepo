/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.implementation;

import beans.Answer;
import command.Command;
import dao.AnswerDAO;
import dao.StudentDAO;
import dao.TestDAO;
import dao.logic.DAOFactory;
import dictionaries.BeanType;
import dictionaries.UserType;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import propertiesmanagement.pages.PageManager;

/**
 *
 * @author Vinokurov
 */
public class EditBeanCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
        UserType user=(UserType) request.getSession(true).getAttribute("userType");
        switch(user){
            case ROOT:
                return executeAsRoot(request,response);
            case TUTOR:
                return executeAsTutor(request,response);
            case STUDENT:
                return executeAsStudentAnswer(request,response);
            default:
                throw new IllegalArgumentException();
        }
            
    }

    private String executeAsRoot(HttpServletRequest request, HttpServletResponse response) {
        String beanType=request.getParameter("beanType");
        switch(beanType){
            case "student":
                return executeAsRootStudent(request,response);
            case "tutor":
                return executeAsRootTutor(request,response);
            default:
                throw new IllegalArgumentException();
        }
        
    }

    private String executeAsTutor(HttpServletRequest request, HttpServletResponse response) {
        String beanType=request.getParameter("beanType");
        switch(beanType){
            case "test":
                return executeAsTutorTest(request,response);
            case "question":
                return executeAsTutorQuestion(request,response);
            case "answer":
                return executeAsTutorAnswer(request,response);
            default:
                throw new IllegalArgumentException();
        }
    }

    private String executeAsRootStudent(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private String executeAsRootTutor(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private String executeAsTutorTest(HttpServletRequest request, HttpServletResponse response) {
        TestDAO testDAO=DAOFactory.getTestDAO();
        
        if (request.getParameter("command").equals("markready")) {
            int testID=Integer.valueOf(request.getParameter("idtest"));
            testDAO.markReady(testID);
            return PageManager.getInstance().getProperty("TUTORHOME");
        } else {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    private String executeAsTutorQuestion(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private String executeAsTutorAnswer(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private String executeAsStudentAnswer(HttpServletRequest request, HttpServletResponse response) {
        StudentDAO studentDAO=DAOFactory.getStudentDAO();  
        AnswerDAO answerDAO=DAOFactory.getAnswerDAO();
        
        Integer studentID=(Integer)request.getSession().getAttribute("userID");
        Integer answerID=Integer.valueOf(request.getParameter("idanswer"));
        Integer questionID=Integer.valueOf(request.getParameter("idquestion"));
        
        studentDAO.alterAnswer(studentID, answerID);
        
        List<Answer> list=answerDAO.getAnswers(BeanType.QUESTION,questionID);
        
        //markedTrue - answers for this question which current student had chosen
        List<Answer> markedTrue=answerDAO.getAnswers(BeanType.STUDENT, studentID);
        for(Answer answer:list){
            if(!(markedTrue.contains(answer))){
                markedTrue.remove(answer);
            }
        }
        
        request.setAttribute("answerList", list);
        request.setAttribute("questionID", questionID);
        request.setAttribute("markedTrue", markedTrue);
        
        return PageManager.getInstance().getProperty("QUESTIONPAGE");
        
        
    }

    
}
