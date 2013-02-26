/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package propertiesmanagement.messages;

import java.util.ResourceBundle;

/**
 *
 * @author Vinokurov
 */
public class MessageManager {
     private static MessageManager instance;
     private ResourceBundle resourceBundle;
     
    private final static String BUNDLE="propertiesmanagement.messages.messages";
    private final static String LOGIN_ERROR_MESSAGE="LOGIN_ERROR_MESSAGE";
    private final static String SERVLET_EXCEPTION_ERROR_MESSAGE= "SERVLET_EXCEPTION_ERROR_MESSAGE";
    private final static String IO_EXCEPTION_ERROR_MESSAGE="IO_EXCEPTION_ERROR_MESSAGE";       
    
    public static MessageManager getInstance(){
         if (instance == null){
             instance = new MessageManager();
             instance.resourceBundle = ResourceBundle.getBundle(BUNDLE);
         }
         return instance;
     }
     public String getProperty(String key){
         return (String)resourceBundle.getObject(key);
     }   
}
