package connection;

import java.sql.Connection;
import java.sql.SQLException;
import propertiesmanagement.globalvariables.VariablesManager;

/**
 *
 * @author Винокуров
 */
public class ConnectorFactory {
    private static DBConnector getConnector(DBName dbName)throws SQLException{
        switch(dbName){
            case MYSQL:
                return MySQLConnector.getInstance();
            default:
                throw new UnsupportedOperationException("Only MySQL supported");  
        }
    }    
    
    private static DBConnector getConnector(String dbName)throws SQLException{
       return getConnector(DBName.valueOf(dbName));
    }
    
    //NOTE! next two methods have different names:
    private static DBConnector getConnector()throws SQLException{
        return getConnector(VariablesManager.getInstance().getProperty("DB_CHOSEN"));
    }
    
    public static Connection getConnection()throws SQLException{
        return getConnector().getConnection();
    }
    
}
