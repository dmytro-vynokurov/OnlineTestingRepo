/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Tutor;
import dao.logic.AbstractDAO;
import java.util.List;

/**
 *
 * @author Винокуров
 */
public abstract class TutorDAO extends AbstractDAO{
    public abstract List<Tutor> getTutors();
    public abstract Tutor getTutor(int tutorID);
    public abstract Tutor getTutor(String login);
    public abstract void addTutor(String firstName,String secondName,String middleName,
            String login,String password);
    public abstract void updateTutor(int tutorID,String newFirstName,
            String newSecondName,String newMiddleName,
            String newLogin,String newPassword);
    public abstract Tutor checkLogin(String login,String password);
    
    public void updateTutor(Tutor tutor){
        updateTutor(tutor.getIDTutor(),tutor.getFirstName(),
                tutor.getSecondName(),tutor.getMiddleName(),
                tutor.getLogin(),tutor.getPassword());
    }
   
}
