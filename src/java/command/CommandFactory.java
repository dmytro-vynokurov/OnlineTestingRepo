/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import command.implementation.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Vinokurov
 */
public class CommandFactory {
    public static Command getCommand(HttpServletRequest request){
        String commandType=request.getParameter("command");
        switch(commandType){
            case "add":
                Logger.getLogger(CommandFactory.class.getName()).log(Level.INFO, "Add command started");
                return new AddBeanCommand();
            case "edit":
            case "markready":
                Logger.getLogger(CommandFactory.class.getName()).log(Level.INFO, "Edit command started");
                return new EditBeanCommand();
            case "show":
                Logger.getLogger(CommandFactory.class.getName()).log(Level.INFO, "Show command started");
                return new ShowBeanCommand();
            case "login":
                Logger.getLogger(CommandFactory.class.getName()).log(Level.INFO, "Login command started");
                return new LoginCommand();
            case "logout":
                Logger.getLogger(CommandFactory.class.getName()).log(Level.INFO, "Logout command started");
                return new LogoutCommand();
            default:
                return new NoCommand();
        }
    }
}