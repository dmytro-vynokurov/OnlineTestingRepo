/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package propertiesmanagement.pages;

import java.util.ResourceBundle;

/**
 *
 * @author Vinokurov
 */
public class PageManager {
         private static PageManager instance;
     private ResourceBundle resourceBundle;
     
    private final static String BUNDLE="propertiesmanagement.pages.pages";
    private final static String INDEX="INDEX";
    private final static String LOGIN="LOGIN";
    private final static String ROOTHOME="ROOTHOME";
    private final static String STUDENTHOME="STUDENTHOME";
    private final static String TUTORHOME="TUTORHOME";  
    private final static String ERROR="ERROR";
    private final static String TESTPAGE="TESTPAGE";
    private final static String QUESTIONPAGE="QUESTIONPAGE";    
    
    
    public static PageManager getInstance(){
         if (instance == null){
             instance = new PageManager();
             instance.resourceBundle = ResourceBundle.getBundle(BUNDLE);
         }
         return instance;
     }
     public String getProperty(String key){
         return (String)resourceBundle.getObject(key);
     }   
}
