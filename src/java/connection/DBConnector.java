/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Винокуров
 */
public interface DBConnector {
    public Connection getConnection()throws SQLException;
}
