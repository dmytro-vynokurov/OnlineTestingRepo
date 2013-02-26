/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaries;

/**
 *
 * @author Vinokurov
 */
public enum UserType {
    ROOT("ROOT"),
    STUDENT("STUDENT"),
    TUTOR("TUTOR");
    
    private String name;
    
    UserType(String value){
        this.name=value;
    }
    
    public String getName(){
        return name;
    }
    
}
