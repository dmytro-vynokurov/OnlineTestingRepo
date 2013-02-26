/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import connection.DBConnector;
import propertiesmanagement.connection.mysql.MySQLManager;
import static propertiesmanagement.connection.mysql.MySQLManager.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Винокуров
 */
public class MySQLConnector implements DBConnector{
    
    @Override
    public Connection getConnection() throws SQLException{
        String driver=MySQLManager.getInstance().getProperty("DRIVER");
        String connectionURI=MySQLManager.getInstance().getProperty("CONNECTION_URI");
        String rootLogin=MySQLManager.getInstance().getProperty("ROOT_LOGIN");
        String rootPassword=MySQLManager.getInstance().getProperty("ROOT_PASSWORD");
        
        
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection result;
        result=DriverManager.getConnection(connectionURI,rootLogin,
                rootPassword);
        return result;
    }
    
    
    private MySQLConnector() {
    }
    
    public static MySQLConnector getInstance() {
        return MySQLConnectionHolder.INSTANCE;
    }
    
    private static class MySQLConnectionHolder {

        private static final MySQLConnector INSTANCE = new MySQLConnector();
    }
}
