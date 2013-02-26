/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import beans.Subject;
import dao.SubjectDAO;
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
public class MySQLSubjectDAO extends SubjectDAO{
    
    private final static String SELECT="SELECT sj.idsubject,sj.subjectname "
            + "FROM Subject sj ";
    
    private final static int IDSUBJECT=1;
    private final static int SUBJECTNAME=2;
    
    private Request request;
    
    public MySQLSubjectDAO(){
        request=new Request();
    }

    @Override
    public List<Subject> getSubjects() {
        request.setStatement(SELECT+";");
        return fetchBeans(request.executeQuery());
    }

    @Override
    public Subject getSubject(int subjectID) {
        request.setStatement(SELECT+"WHERE sj.idsubject=?;");
        request.setParameters(subjectID);
        List<Subject> list=fetchBeans(request.executeQuery());
        if(list.size()>1){
            try {
                throw new Exception("Fetched many subjects");
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
    public void addSubject(String subjectName) {
        request.setStatement("INSERT INTO Subject(subjectname)"
                + "VALUES(?);");
        request.setParameters(subjectName);
        request.executeUpdate();
    }
    
    private Subject fetchBean(ResultSet rs){
         try {
            Subject subject=new Subject();
            
            subject.setIDSubject(rs.getInt(IDSUBJECT));
            subject.setSubjectName(rs.getString(SUBJECTNAME));
            return subject;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLQuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }       
    }
    
    private List<Subject> fetchBeans(ResultSet rs){
        List<Subject> result=new ArrayList<>();
        try {
            while(rs.next()){
                result.add(fetchBean(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLSubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        request.closeConnection();
    }
    
}
