/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import beans.Test;
import dao.TestDAO;
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
public class MySQLTestDAO extends TestDAO{
    
    private static final String SELECT="SELECT t.idtest,t.idsubject,t.idtutor,t.ready "
            + "FROM Test t ";
    
    private final static int IDTEST=1;
    private final static int IDSUBJECT=2;
    private final static int IDTUTOR=3;
    private final static int READY=4;
    
    private Request request;
    
    public MySQLTestDAO(){
        request=new Request();
    }

    @Override
    public List<Test> getTests() {
        request.setStatement(SELECT+";");
        return fetchBeans(request.executeQuery());
    }

    @Override
    public List<Test> getTests(BeanType beanType, int beanID) {
        switch(beanType){
            case STUDENT:
                return getTestsByStudentID(beanID);
            case SUBJECT:
                return getTestsBySubjectID(beanID);
            case TUTOR:
                return getTestsByTutorID(beanID);
            default:
                throw new IllegalArgumentException();
                
        }
    }

    @Override
    public Test getTest(int testID) {
        request.setStatement(SELECT+"WHERE t.idtest=?;");
        request.setParameters(testID);
        List<Test> list=fetchBeans(request.executeQuery());
        if(list.size()>1){
            try {
                throw new Exception("Fetched many tests");
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
    public void addTest(int tutorID, int subjectID) {
        request.setStatement("INSERT INTO Test(idtutor,idsubject,ready) "
                + "VALUES(?,?,false);");
        request.setParameters(tutorID,subjectID);
        request.executeUpdate();
    }

    @Override
    public void markReady(int testID) {
        request.setStatement("UPDATE Test SET ready=true WHERE idtest=?;");
        request.setParameters(testID);
        request.executeUpdate();
    }
    
    private Test fetchBean(ResultSet rs){
        try {
            Test test=new Test();
            
            test.setIDTest(rs.getInt(IDTEST));
            test.setIDSubject(rs.getInt(IDSUBJECT));
            test.setIDTutor(rs.getInt(IDTUTOR));
            test.setReady(rs.getBoolean(READY));
            return test;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTestDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private List<Test> fetchBeans(ResultSet rs){
        List<Test> result=new ArrayList<>();
        try {
            while(rs.next()){
                result.add(fetchBean(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLTestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private List<Test> getTestsByTutorID(int tutorID) {
        request.setStatement(SELECT+"WHERE t.idtutor="+tutorID+";");
        return fetchBeans(request.executeQuery());
    }

    private List<Test> getTestsBySubjectID(int subjectID) {
        request.setStatement(SELECT+"WHERE t.idsubject="+subjectID+";");
        return fetchBeans(request.executeQuery());
    }

    private List<Test> getTestsByStudentID(int studentID) {
        request.setStatement(
                SELECT + ", StudentAnswer sa , Answer a , Question q "
                + "WHERE ?=sa.idstudent and "
                + "sa.idanswer=a.idanswer and "
                + "a.idquestion=q.idquestion and "
                + "q.idtest=t.idtest;");
        request.setParameters(studentID);
        return fetchBeans(request.executeQuery());
    }
    
    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        request.closeConnection();
    }
    
}
