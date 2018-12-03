
package com.scrum.project;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.json.simple.parser.ParseException;

@ManagedBean
@RequestScoped
public class DataCollection {
    
    private String message;
    //Project Data
    private Project project=new Project();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    
        
    public List<Project> projects(String user)
    {
        List<Project> lst=new ArrayList<>();
        try {
            
            DBConnection conn=new DBConnection();
            ResultSet result=conn.Data_Source("select idproject,projectname,ownerid from project where ownerid='" + user + "'");
            while (result.next())
            {
                lst.add(new Project(
                        Integer.valueOf(result.getString("idproject")),
                        result.getString("projectname"),
                        new Person(result.getString("ownerid"))
                        )
                );
            }
            return lst;
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            return lst;
        }
    }
    
    public String saveProject(String user)
    {        
        try {
            Project proj=new Project();
            Person owner=new Person();
            owner.setIdpers(user);
            project.setOwner(owner);
            proj.save(project);
            message="Enregistrement effectué avec succès";
            return "ConfirmMsg";
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            message=ex.getMessage();
            return "ConfirmMsg";
        }
    }
    
    public DataCollection() {
    }
    
}
