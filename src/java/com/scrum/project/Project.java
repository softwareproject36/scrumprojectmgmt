
package com.scrum.project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import org.json.simple.parser.ParseException;

public class Project {
    private int idProj;
    private String projectName;
    private Person owner;

    public int getIdProj() {
        return idProj;
    }

    public void setIdProj(int idProj) {
        this.idProj = idProj;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
    
    

    public Project() {
    }
 
    public Project(int idProj,String projectName,Person owner)
    {
        this.idProj=idProj;
        this.projectName=projectName;
        this.owner=owner;
    }
    
    public void save(Project project) throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException
    {
        DBConnection conn=new DBConnection();
        conn.Execute_Query("INSERT INTO project(idproject,projectname,ownerid) VALUES('"
                + project.idProj + "','"
                + project.projectName.replace("'", "''") + "','" 
                + project.owner.getIdpers() + "')"
        );
    }
    
}
