package propertiesmanagement.connection.mysql;

import java.util.ResourceBundle;

/**
 *
 * @author Vinokurov
 */
public class MySQLManager {
    private static MySQLManager instance;
    private ResourceBundle resourceBundle;
    
    private final static String BUNDLE_NAME="propertiesmanagement.connection.mysql.access";
    private final static String CONNECTION_URI="CONNECTION_URI";
    private final static String DRIVER="DRIVER";
    private final static String ROOT_LOGIN="ROOT_LOGIN";
    private final static String ROOT_PASSWORD="ROOT_PASSWORD";
    
    public static MySQLManager getInstance() {
        if (instance == null) {
            instance = new MySQLManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
    
    
}
