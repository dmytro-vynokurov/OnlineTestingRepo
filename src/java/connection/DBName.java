/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

/**
 *
 * @author Винокуров
 */
public enum DBName {
    MYSQL("MYSQL"),
    ORACLE("ORACLE");
    
    private String name;
    
    DBName(String name){
        this.name=name;
    }
    
    public String getName(){
        return name;
    }
    
}
