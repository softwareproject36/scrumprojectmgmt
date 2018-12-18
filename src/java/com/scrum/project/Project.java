
package com.scrum.project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import org.json.simple.parser.ParseException;

public class Project {
    private String idProj;
    private String projectName;
    private String projectOwner;
    
    public Project() {
    }
 
    public Project(String idProj,String projectName)
    {
        this.idProj=idProj;
        this.projectName=projectName;
    }

    public Project(String idProj, String projectName, String projectOwner) {
        this.idProj = idProj;
        this.projectName = projectName;
        this.projectOwner = projectOwner;
    }
    
    public String getIdProj() {
        return idProj;
    }

    public void setIdProj(String idProj) {
        this.idProj = idProj;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }
    
    public void save(Project project) throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException
    {
        DBConnection conn=new DBConnection();
        conn.Execute_Query("INSERT INTO scrum_project(id_project,project_name) VALUES('"
                + project.idProj + "','"
                + project.projectName.replace("'", "''") + "')" 
        );
    }
    
}
