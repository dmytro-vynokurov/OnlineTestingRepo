/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import beans.Student;
import dao.StudentDAO;
import dictionaries.BeanType;
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
public class MySQLStudentDAO extends StudentDAO {

    //request template
    private final static String SELECT = "SELECT s.idstudent,s.firstname,s.secondname,"
            + "s.middlename,s.login,s.password FROM Student s ";
    // what does each column in result set mean: MEANING=columnid
    private final static int IDSTUDENT = 1;
    private final static int FIRSTNAME = 2;
    private final static int SECONDNAME = 3;
    private final static int MIDDLENAME = 4;
    private final static int LOGIN = 5;
    private final static int PASSWORD = 6;
    
    private Request request;
    
    public MySQLStudentDAO(){
        request=new Request();
    }
    
    @Override
    public Student getStudent(int studentID) {
        request.setStatement(SELECT + "WHERE s.idstudent=?;");
        request.setParameters(studentID);
        List<Student> list=fetchBeans(request.executeQuery());
        if(list.size()>1){
            try {
                throw new Exception("fetched many students");
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
    public Student getStudent(String login){
        request.setStatement(SELECT + "WHERE s.login=?;");
        request.setParameters(login);
        List<Student> list=fetchBeans(request.executeQuery());
        if(list.size()>1){
            try {
                throw new Exception("fetched many students");
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
    public List<Student> getStudents() {
        request.setStatement(SELECT+";");
        return fetchBeans(request.executeQuery());
    }

    @Override
    public List<Student> getStudents(BeanType beanType, int beanID) {
        switch (beanType) {
            case SUBJECT:
                return getStudentBySubjectID(beanID);
            case TEST:
                return getStudentByTestID(beanID);
            default:
                throw new IllegalArgumentException();
        }
    }

    private List<Student> getStudentBySubjectID(int subjectID) {
        request.setStatement(
                SELECT + ", StudentAnswer sa , Answer a , Question q "
                + ", Test t , Subject sj "
                + "WHERE s.idstudent=sa.idstudent and "
                + "sa.idanswer=a.idanswer and "
                + "a.idquestion=q.idquestion and "
                + "q.idtest=t.idtest and "
                + "t.idsubject=?;");
        request.setParameters(subjectID);
        return fetchBeans(request.executeQuery());

    }

    private List<Student> getStudentByTestID(int testID) {
        request.setStatement(
                SELECT + ", StudentAnswer sa , Answer a , Question q "
                + ", Test t "
                + "WHERE s.idstudent=sa.idstudent and "
                + "sa.idanswer=a.idanswer and "
                + "a.idquestion=q.idquestion and "
                + "q.idtest=?;");
        request.setParameters(testID);
        return fetchBeans(request.executeQuery());
    }

    @Override
    public void addStudent(String firstName, String secondName, String middleName,
            String login, String password) {

        request.setStatement(
                "INSERT INTO Student(firstname,secondname,middlename,login,password)"
                + "values(?,?,?,?,?);");
        request.setParameters(firstName, secondName, middleName, login, password);
        request.executeUpdate();

    }

    @Override
    public void updateStudent(int studentID, String newFirstName,
            String newSecondName, String newMiddleName,
            String newLogin, String newPassword) {
        request.setStatement("UPDATE TABLE Student "
                + "SET firstname=?,secondname=?,middlename=?,"
                + "login=?,password=? "
                + "WHERE idstudent=?");
        request.setParameters(newFirstName, newSecondName, newMiddleName,
                newLogin, newPassword, studentID);
        request.executeUpdate();

    }

    private Student fetchBean(ResultSet rs) {
        try {
            Student student = new Student();

            student.setIDStudent(rs.getInt(IDSTUDENT));
            student.setFirstName(rs.getString(FIRSTNAME));
            student.setSecondName(rs.getString(SECONDNAME));
            student.setMiddleName(rs.getString(MIDDLENAME));
            student.setLogin(rs.getString(LOGIN));
            student.setPassword(rs.getString(PASSWORD));
            return student;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLQuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private List<Student> fetchBeans(ResultSet rs) {
        List<Student> result = null;

        try {
            result = new ArrayList<>();
            while (rs.next()) {
                result.add(fetchBean(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLQuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public Student checkLogin(String login, String password) {
        Student student = null;
        try {
            student = getStudent(login);
            if (student.getPassword().equals(password)) {
                return student;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void addAnswer(int studentID,int answerID) {
        request.setStatement("INSERT INTO StudentAnswer(idstudent,idanswer) "
                + "VALUES(?,?)");
        request.setParameters(studentID,answerID);
        request.executeUpdate();
    }
    
    @Override
    public void removeAnswer(int studentID,int answerID){
        request.setStatement("DELETE FROM StudentAnswer WHERE idstudent=? AND "
                + "idanswer=?;");
        request.setParameters(studentID,answerID);
        request.executeUpdate();
    }

    @Override
    public void alterAnswer(int studentID, int answerID) {
        try {
            ResultSet rs=request.executeQuery("SELECT * FROM StudentAnswer WHERE "
                    + "idstudent=? AND idanswer=?;", studentID,answerID);
            boolean flag=false;
            while(rs.next()){
                flag=true;
            }
            if(flag){
                removeAnswer(studentID,answerID);
            }else{
                addAnswer(studentID,answerID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLStudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        request.closeConnection();
    }
}
