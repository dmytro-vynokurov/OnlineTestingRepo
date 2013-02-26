/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Subject;
import dao.logic.AbstractDAO;
import java.util.List;

/**
 *
 * @author Винокуров
 */
public abstract class SubjectDAO extends AbstractDAO{
    public abstract List<Subject> getSubjects();
    public abstract Subject getSubject(int subjectID);
    public abstract void addSubject(String subjectName);
    
}
