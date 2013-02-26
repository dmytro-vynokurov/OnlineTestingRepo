package request;

import connection.ConnectionPool;
import connection.ConnectorFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinokurov
 */
public class Request {

    private PreparedStatement ps;
    
    public Request(){
    }

    public Request(String sql) {
        try {
            //ps = ConnectorFactory.getConnection().prepareStatement(sql);
            ps=ConnectionPool.getInstance().getConnection().prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setStatement(String sql){
        try {
            ps = ConnectorFactory.getConnection().prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setParameter(int parameterIndex, Object obj) {
        try {
            String parameterClassName = obj.getClass().getName();
            //worked wrong in case, so it was explicitly declared here
            if(parameterClassName.equals("java.lang.Boolean")){
                    ps.setBoolean(parameterIndex, (boolean) ((Boolean) obj));
                    return;
            }
            //next line needs JDK7
            switch (parameterClassName) {
                case "java.lang.Integer":
                case "int":
                    ps.setInt(parameterIndex, (int) ((Integer) obj));
                    break;
                case "java.lang.String":
                    ps.setString(parameterIndex, (String) obj);
                    break;
                case "java.lang.Boolean":
                case "boolean":
                    ps.setBoolean(parameterIndex, (boolean) ((Boolean) obj));
                default:
                    throw new IllegalArgumentException();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParameters(Object... obj) {
        for (int i = 0; i < obj.length; i++) {
            setParameter(i + 1, obj[i]);
        }
    }

    public ResultSet executeQuery()  {
        try {
            return ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet executeQuery(String sql){
        try {
            ps = ConnectorFactory.getConnection().prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ResultSet executeQuery(String sql,Object ... obj) {
        this.setStatement(sql);
        this.setParameters(obj);
        return this.executeQuery();
    }
    
    public int executeUpdate(){
        try {
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int executeUpdate(String sql)  {
        try {
            ps=ConnectorFactory.getConnection().prepareStatement(sql);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
            return  0;
        }
    }
    
    public int executeUpdate(String sql,Object ... obj){
        this.setStatement(sql);
        this.setParameters(obj);
        return this.executeUpdate();
    }    
    
    public void closeConnection(){
        try{
            ps.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
