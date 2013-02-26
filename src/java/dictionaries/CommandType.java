/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaries;

/**
 *
 * @author Vinokurov
 */
public enum CommandType {
    LOGIN("LOGIN"),
    LOGOUT("LOGOUT"),
    NOCOMMAND("NOCOMMAND"),
    ADDBEAN("ADDBEAN"),
    EDITBEAN("EDITBEAN"),
    SHOWBEAN("SHOWBEAN");
    
    private String name;
    
    CommandType(String name){
        this.name=name;
    }
    
    public String getName(){
        return name;
    }
        
}
