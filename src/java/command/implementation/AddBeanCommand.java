/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.implementation;

import beans.Answer;
import beans.Question;
import command.Command;
import dao.AnswerDAO;
import dao.QuestionDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import dao.TutorDAO;
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
public class AddBeanCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        UserType user=(UserType) request.getSession(true).getAttribute("userType");
        switch(user){
            case ROOT:
                return executeAsRoot(request,response);
            case TUTOR:
                return executeAsTutor(request,response);
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
            case "subject":
                return executeAsRootSubject(request,response);
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
        StudentDAO studentDAO=DAOFactory.getStudentDAO();        
        
        String firstName=request.getParameter("firstName");
        String secondName=request.getParameter("secondName");
        String middleName=request.getParameter("middleName");
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        
        if(!login.equals("root")){
            studentDAO.addStudent(firstName, secondName, middleName, login, password);
        }
        
        return PageManager.getInstance().getProperty("ROOTHOME");
    }

    private String executeAsRootTutor(HttpServletRequest request, HttpServletResponse response) {
        TutorDAO tutorDAO=DAOFactory.getTutorDAO();        
        
        String firstName=request.getParameter("firstName");
        String secondName=request.getParameter("secondName");
        String middleName=request.getParameter("middleName");
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        
        if(!login.equals("root")){
            tutorDAO.addTutor(firstName, secondName, middleName, login, password);
        }
        
        return PageManager.getInstance().getProperty("ROOTHOME");
    }

    private String executeAsRootSubject(HttpServletRequest request, HttpServletResponse response) {
        SubjectDAO subjectDAO=DAOFactory.getSubjectDAO();
        
        String subjectName=request.getParameter("subjectName");
        
        subjectDAO.addSubject(subjectName);
        
        return PageManager.getInstance().getProperty("ROOTHOME");
    }

    private String executeAsTutorTest(HttpServletRequest request, HttpServletResponse response) {
        TestDAO testDAO=DAOFactory.getTestDAO();
        
        int tutorID=(Integer)request.getSession().getAttribute("userID");
        int subjectID=Integer.valueOf(request.getParameter("idsubject"));
        
        testDAO.addTest(tutorID, subjectID);
        
        return PageManager.getInstance().getProperty("TUTORHOME");
    }

    private String executeAsTutorQuestion(HttpServletRequest request, HttpServletResponse response) {
        QuestionDAO questionDAO = DAOFactory.getQuestionDAO();
        
        Integer testID=Integer.valueOf(request.getParameter("idtest"));
        String text=request.getParameter("text");
        String oneAnswerString=request.getParameter("oneanswer");
        boolean oneAnswer=false;
        if((oneAnswerString!=null)&&(oneAnswerString.equals("on")))oneAnswer=true;
        
        questionDAO.addQuestion(testID, text, oneAnswer);
        List<Question> list=questionDAO.getQuestions(testID);
        
        request.setAttribute("testID", testID);
        request.setAttribute("questionList", list);
        
        return PageManager.getInstance().getProperty("TESTPAGE");
    }

    private String executeAsTutorAnswer(HttpServletRequest request, HttpServletResponse response) {
        AnswerDAO answerDAO=DAOFactory.getAnswerDAO();
        
        Integer questionID=Integer.valueOf(request.getParameter("idquestion"));
        String text=request.getParameter("text");
        String isCorrectString=request.getParameter("iscorrect");
        boolean isCorrect=false;
        
        if((isCorrectString!=null)&&(isCorrectString.equals("on")))isCorrect=true;
        
        answerDAO.addAnswer(questionID, text, isCorrect);
        
        List<Answer> list=answerDAO.getAnswers(BeanType.QUESTION, questionID);
        
        request.setAttribute("questionID", questionID);
        request.setAttribute("answerList", list);
        
        return PageManager.getInstance().getProperty("QUESTIONPAGE");
    }


    
}
