/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaries;

/**
 *
 * @author Vinokurov
 */
public enum BeanType {
    ANSWER("ANSWER"),
    QUESTION("QUESTION"),
    STUDENT("STUDENT"),
    SUBJECT("SUBJECT"),
    TEST("TEST"),
    TUTOR("TUTOR");
    
    
    private String name;
    
    BeanType(String name){
        this.name=name;
    }
    
    public String getName(){
        return name;
    }
    
}
