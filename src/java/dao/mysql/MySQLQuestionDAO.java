/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import beans.Question;
import dao.QuestionDAO;
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
public class MySQLQuestionDAO extends QuestionDAO{
    
    //request template
    private static final String SELECT="SELECT q.idquestion,"
            + "q.idtest,q.text,q.oneanswer FROM question q ";
    
    // what does each column in result set mean: MEANING=columnid
    private final static int IDQUESTION=1;
    private final static int IDTEST=2;
    private final static int TEXT=3;
    private final static int ONEANSWER=4;
    
    private Request request;
    
    public MySQLQuestionDAO(){
        request=new Request();
    }

    @Override
    public List<Question> getQuestions(int testID) {
        request.setStatement(
                SELECT+" WHERE q.idtest=?;"
                );
        request.setParameters(testID);
        return fetchBeans(request.executeQuery());
    }

    @Override
    public Question getQuestion(int questionID) {
        request.setStatement(
                SELECT+"WHERE q.idquestion=?;"
                );       
        request.setParameters(questionID);
                
        List<Question> list=fetchBeans(request.executeQuery());
        if(list.size()>1){
            try {
                throw new Exception("Fetched many questions");
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
    public void addQuestion(int testID,String text, boolean oneAnswer) {
        request.setStatement(
                "INSERT INTO Question(idtest,text,oneanswer)"
                + " values(?,?,?);"
                );
        request.setParameters(testID,text,oneAnswer);
        request.executeUpdate();
    }

    @Override
    public void updateQuestion(int questionID, int newTestID, String newText, boolean newOneAnswer) {
        request.setStatement("UPDATE TABLE Question "
                + "SET idtest=?,text=?,oneanswer=? "
                + "WHERE idquestion=?;");
        request.setParameters(newTestID,newText,newOneAnswer,questionID);
        request.executeUpdate();
    }
    
    // converting data from current row of result set to Bean
    private Question fetchBean(ResultSet rs){
        try {
            Question question = new Question();
            
            question.setIDQuestion(rs.getInt(IDQUESTION));
            question.setIDTest(rs.getInt(IDTEST));
            question.setText(rs.getString(TEXT));   
            question.setOneAnswer(rs.getBoolean(ONEANSWER));
            return question;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLQuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private List<Question> fetchBeans(ResultSet rs){
        List<Question> result=null;
        
        try {
            result=new ArrayList<>();
            while(rs.next()){
                result.add(fetchBean(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLQuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        request.closeConnection();
    }
    
}
