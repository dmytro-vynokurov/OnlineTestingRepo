/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.implementation;

import beans.Answer;
import beans.Question;
import beans.Student;
import beans.Subject;
import beans.Test;
import beans.Tutor;
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
public class ShowBeanCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserType user = (UserType) request.getSession(true).getAttribute("userType");
        switch (user) {
            case ROOT:
                return executeAsRoot(request, response);
            case TUTOR:
                return executeAsTutor(request, response);
            case STUDENT:
                return executeAsStudent(request, response);
            default:
                throw new IllegalArgumentException();
        }
    }

    private String executeAsRoot(HttpServletRequest request, HttpServletResponse response) {
        String beanType = request.getParameter("beanType");
        switch (beanType) {
            case "student":
                return executeAsRootStudent(request, response);
            case "tutor":
                return executeAsRootTutor(request, response);
            case "subject":
                return executeSubject(request, response);
            default:
                throw new IllegalArgumentException();
        }
    }

    private String executeAsTutor(HttpServletRequest request, HttpServletResponse response) {
        String beanType = request.getParameter("beanType");
        switch (beanType) {
            case "student":
                return executeAsTutorStudent(request, response);
            case "subject":
                return executeSubject(request, response);
            case "test":
                return executeTest(request, response);
            case "question":
                return executeQuestion(request, response);
            case "answer":
                return executeAsTutorAnswer(request, response);
            default:
                throw new IllegalArgumentException();
        }
    }

    private String executeAsStudent(HttpServletRequest request, HttpServletResponse response) {
        String beanType = request.getParameter("beanType");
        switch (beanType) {
            case "test":
                return executeTest(request, response);
            case "subject":
                return executeSubject(request, response);
            case "question":
                return executeQuestion(request, response);
            case "answer":
                return executeAsStudentAnswer(request, response);
            default:
                throw new IllegalArgumentException();
        }
    }

    private String executeAsRootStudent(HttpServletRequest request, HttpServletResponse response) { 
        StudentDAO studentDAO=DAOFactory.getStudentDAO();        
        
        List<Student> list=studentDAO.getStudents();       
        
        int showTo=Integer.valueOf(request.getParameter("showTo"));
        if(showTo>list.size())showTo=list.size();
        
        request.setAttribute("studentList", list.subList(0, showTo));              
        
        return PageManager.getInstance().getProperty("ROOTHOME");
    }

    private String executeAsRootTutor(HttpServletRequest request, HttpServletResponse response) {        
        TutorDAO tutorDAO=DAOFactory.getTutorDAO();        
        
        List<Tutor> list=tutorDAO.getTutors();       
        
        int showTo=Integer.valueOf(request.getParameter("showTo"));
        if(showTo>list.size())showTo=list.size();
        
        request.setAttribute("tutorList", list.subList(0, showTo));              
        
        return PageManager.getInstance().getProperty("ROOTHOME");
    }

    private String executeSubject(HttpServletRequest request, HttpServletResponse response) {    
        SubjectDAO subjectDAO=DAOFactory.getSubjectDAO();        

        List<Subject> list=subjectDAO.getSubjects();
        
        int showTo=Integer.valueOf(request.getParameter("showTo"));
        if(showTo>list.size())showTo=list.size();
        
        request.setAttribute("subjectList", list.subList(0, showTo));              
        
        UserType userType=(UserType)request.getSession().getAttribute("userType");
        switch(userType){
            case ROOT:
                return PageManager.getInstance().getProperty("ROOTHOME");
            case TUTOR:
                return PageManager.getInstance().getProperty("TUTORHOME");
            case STUDENT:
                return PageManager.getInstance().getProperty("STUDENTHOME");
            default:
                throw new IllegalArgumentException();
        }
    }

    private String executeAsTutorStudent(HttpServletRequest request, HttpServletResponse response) {
        StudentDAO studentDAO=DAOFactory.getStudentDAO();        
        
        List<Student> list=studentDAO.getStudents();       
        
        int showTo=Integer.valueOf(request.getParameter("showTo"));
        if(showTo>list.size())showTo=list.size();
        
        request.setAttribute("studentList", list.subList(0, showTo));              
        
        return PageManager.getInstance().getProperty("TUTORHOME");
    }

    private String executeTest(HttpServletRequest request, HttpServletResponse response) {
        TestDAO testDAO=DAOFactory.getTestDAO();
        
        List<Test> list=testDAO.getTests();
        
        if(request.getSession().getAttribute("userType").equals(BeanType.STUDENT)){
            for(Test test:list){
                if(!test.getReady()){
                    list.remove(test);
                }
            }
        }
        
        int showTo=Integer.valueOf(request.getParameter("showTo"));
        if(showTo>list.size())showTo=list.size();
        
        request.setAttribute("testList", list.subList(0, showTo));    
        
        UserType userType=(UserType)request.getSession().getAttribute("userType");
        switch(userType){
            case TUTOR:
                return PageManager.getInstance().getProperty("TUTORHOME");
            case STUDENT:
                return PageManager.getInstance().getProperty("STUDENTHOME");
            default:
                throw new IllegalArgumentException();
        }
    }

    private String executeQuestion(HttpServletRequest request, HttpServletResponse response) {
        QuestionDAO questionDAO=DAOFactory.getQuestionDAO();
        
        Integer testID=Integer.valueOf(request.getParameter("idtest"));
        
        List<Question> list=questionDAO.getQuestions(testID);
        
        request.setAttribute("questionList", list);
        request.setAttribute("testID", testID);
        
        return PageManager.getInstance().getProperty("TESTPAGE");
    }

    private String executeAsTutorAnswer(HttpServletRequest request, HttpServletResponse response) {
        return executeAsStudentAnswer(request,response);
    }

    private String executeAsStudentAnswer(HttpServletRequest request, HttpServletResponse response) {
        AnswerDAO answerDAO=DAOFactory.getAnswerDAO();
        
        Integer questionID=Integer.valueOf(request.getParameter("idquestion"));
        Integer studentID=(Integer)request.getSession().getAttribute("userID");
        Integer testID=Integer.valueOf(request.getParameter("idtest"));
        
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
        request.setAttribute("testID",testID);
        
        return PageManager.getInstance().getProperty("QUESTIONPAGE");
    }
}
