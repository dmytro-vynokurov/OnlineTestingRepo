/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package propertiesmanagement.globalvariables;

import java.util.ResourceBundle;

/**
 *
 * @author Vinokurov
 */
public class VariablesManager {

    private static VariablesManager instance;
    private ResourceBundle resourceBundle;
    private final static String BUNDLE_NAME = "propertiesmanagement.globalvariables.variables";
    private final static String DB_CHOSEN = "DB_CHOSEN";
    private final static String SERVER_TYPE = "SERVER_TYPE";

    public static VariablesManager getInstance() {
        if (instance == null) {
            instance = new VariablesManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
