/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import beans.Answer;
import dao.AnswerDAO;
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
public class MySQLAnswerDAO extends AnswerDAO {

    // request template
    private static final String SELECT = "SELECT a.idanswer,a.idquestion,"
            + "a.text,a.correct FROM Answer a ";
    // indexes in SQL response
    private static final int IDANSWER = 1;
    private static final int IDQUESTION = 2;
    private static final int TEXT = 3;
    private static final int CORRECT = 4;
    
    private Request request;
    
    public MySQLAnswerDAO(){
        request=new Request();
    }

    @Override
    public List<Answer> getAnswers(BeanType beanType, int beanID) {
        switch (beanType) {
            case STUDENT:
                return getAnswerByStudentID(beanID);
            case QUESTION:
                return getAnswerByQuestionID(beanID);
            default:
                throw new IllegalArgumentException();
        }
    }

    private List<Answer> getAnswerByStudentID(int studentID) {
        ResultSet resultSet;
        request.setStatement(
                SELECT + "JOIN studentanswer sa ON "
                + "a.idanswer=sa.idanswer "
                + "WHERE sa.idstudent=" + studentID + ";");
        resultSet = request.executeQuery();

        return fetchBeans(resultSet);
    }

    private List<Answer> getAnswerByQuestionID(int questionID) {
        request.setStatement(SELECT + "JOIN question q ON "
                + "a.idquestion=q.idquestion "
                + "WHERE q.idquestion=?;");
        request.setParameters(questionID);

        return fetchBeans(request.executeQuery());
    }

    @Override
    public Answer getAnswer(int answerID) {
        request.setStatement(SELECT + "WHERE a.idanswer=?;");
        request.setParameters(answerID);
        List<Answer> list=fetchBeans(request.executeQuery());
        if(list.size()>1){
            try {
                throw new Exception("Fetched many answers");
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
    public void addAnswer(int questionID, String text, boolean isCorrect) {
        request.setStatement("INSERT INTO Answer(idquestion,text,correct) "
                + "VALUES(?,?,?);");
        request.setParameters(questionID,text,isCorrect);
        request.executeUpdate();
    }

    @Override
    public void updateAnswer(int answerID, int newQuestionID, String newText, boolean newCorrect) {
        request.setStatement("UPDATE TABLE Answer "
                + "SET idquestion=?,text=?,correct=? "
                + "WHERE idanswer=?;");
        request.setParameters(newQuestionID, newText, newCorrect, answerID);
        request.executeUpdate();
    }

    //method fetches data from current row of the SQL result to the Bean
    private Answer fetchBean(ResultSet rs) {
        try {
            Answer answer = new Answer();
            answer.setIDAnswer(rs.getInt(IDANSWER));
            answer.setIDQuestion(rs.getInt(IDQUESTION));
            answer.setText(rs.getString(TEXT));
            answer.setCorrect(rs.getBoolean(CORRECT));
            return answer;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAnswerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //fetches all the rows from SQL result table to the list of Answers
    private List<Answer> fetchBeans(ResultSet rs) {
        List<Answer> result = new ArrayList<>();
        try {

            while (rs.next()) {
                result.add(fetchBean(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAnswerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return result;
        }
    }
    
    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        request.closeConnection();
    }
}
