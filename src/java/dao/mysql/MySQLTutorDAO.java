/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import beans.Tutor;
import dao.TutorDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import request.Request;

/**
 *
 * @author Винокуров
 */
public class MySQLTutorDAO extends TutorDAO {

    private final static String SELECT = "SELECT tut.idtutor,tut.firstname,"
            + "tut.secondname,tut.middlename,tut.login,tut.password "
            + "FROM Tutor tut ";
    private final static int IDTUTOR = 1;
    private final static int FIRSTNAME = 2;
    private final static int SECONDNAME = 3;
    private final static int MIDDLENAME = 4;
    private final static int LOGIN = 5;
    private final static int PASSWORD = 6;
    
    private Request request;
    
    public MySQLTutorDAO(){
        request=new Request();
    }

    @Override
    public List<Tutor> getTutors() {
        request.setStatement(SELECT + ";");
        return fetchBeans(request.executeQuery());
    }

    @Override
    public Tutor getTutor(int tutorID) {
        request.setStatement(SELECT + "WHERE tut.idtutor=?;");
        request.setParameters(tutorID);
        List<Tutor> list=fetchBeans(request.executeQuery());
        if(list.size()>1){
            try {
                throw new Exception("Fetched many tutors");
            } catch (Exception ex) {
                Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                return null;
            }
        }else{
            return list.get(0);
        }
    }

    @Override
    public Tutor getTutor(String login) {
        request.setStatement(SELECT + "WHERE tut.login=?;");
        request.setParameters(login);
        List<Tutor> list=fetchBeans(request.executeQuery());
        if(list.size()>1){
            try {
                throw new Exception("Fetched many tutors");
            } catch (Exception ex) {
                Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                return null;
            }
        }else{
            return list.get(0);
        }
    }

    @Override
    public void addTutor(String firstName, String secondName, String middleName, String login, String password) {
        request.setStatement("INSERT INTO Tutor(firstname,secondname,middlename,login,password) "
                + "VALUES(?,?,?,?,?);");
        request.setParameters(firstName, secondName, middleName, login, password);
        request.executeUpdate();
    }

    @Override
    public void updateTutor(int tutorID, String newFirstName, String newSecondName,
            String newMiddleName, String newLogin, String newPassword) {
        request.setStatement("UPDATE TABLE Tutor "
                + "SET firstname=?,secondname=?,middlename=?,"
                + "login=?,password=? "
                + "WHERE idtutor=?");
        request.setParameters(newFirstName, newSecondName, newMiddleName,
                newLogin, newPassword, tutorID);
        request.executeUpdate();

    }

    private Tutor fetchBean(ResultSet rs) {
        try {
            Tutor tutor = new Tutor();

            tutor.setIDTutor(rs.getInt(IDTUTOR));
            tutor.setFirstName(rs.getString(FIRSTNAME));
            tutor.setSecondName(rs.getString(SECONDNAME));
            tutor.setMiddleName(rs.getString(MIDDLENAME));
            tutor.setLogin(rs.getString(LOGIN));
            tutor.setPassword(rs.getString(PASSWORD));

            return tutor;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private List<Tutor> fetchBeans(ResultSet rs) {
        List<Tutor> result = new ArrayList<>();
        try {
            while (rs.next()) {
                result.add(fetchBean(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTutorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public Tutor checkLogin(String login, String password) {
        Tutor tutor = null;

        try {
            tutor = getTutor(login);
            if (tutor.getPassword().equals(password)) {
                return tutor;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
    
    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        request.closeConnection();
    }
}
