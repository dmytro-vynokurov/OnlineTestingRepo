/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.implementation;

import beans.Student;
import beans.Tutor;
import command.Command;
import dao.StudentDAO;
import dao.TutorDAO;
import dao.logic.DAOFactory;
import dictionaries.BeanType;
import dictionaries.UserType;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import propertiesmanagement.pages.PageManager;

/**
 *
 * @author Vinokurov
 */
public class LoginCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        
        TutorDAO tutorDAO=DAOFactory.getTutorDAO();
        StudentDAO studentDAO=DAOFactory.getStudentDAO();
        
        String tutorHome=PageManager.getInstance().getProperty("TUTORHOME");
        String studentHome=PageManager.getInstance().getProperty("STUDENTHOME");
        String rootHome=PageManager.getInstance().getProperty("ROOTHOME");
        String errorPage=PageManager.getInstance().getProperty("ERROR");
        
        if(tutorDAO.checkLogin(login, password)!=null){
            request.getSession(true).setAttribute("userType",UserType.TUTOR);
            Tutor tutor=tutorDAO.getTutor(login);
            request.getSession(true).setAttribute("userID", tutor.getIDTutor());
            return tutorHome;
        }else if(studentDAO.checkLogin(login, password)!=null){
            request.getSession(true).setAttribute("userType",UserType.STUDENT);  
            Student student=studentDAO.getStudent(login);
            request.getSession(true).setAttribute("userID", student.getIDStudent());
            return studentHome;
        }else if((login.equals("root"))&&(password.equals("root"))){
            request.getSession(true).setAttribute("userType",UserType.ROOT);  
            return rootHome;
        }else{
            return errorPage;
        }        
    }
    
}
