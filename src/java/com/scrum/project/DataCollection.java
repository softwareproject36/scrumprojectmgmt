
package com.scrum.project;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.json.simple.parser.ParseException;

@ManagedBean
@RequestScoped
public class DataCollection {
    @ManagedProperty(value="#{login}")
    Login session;
    
    private String message;
    private String navigation;
    
    //Project Data
    private Project project=new Project();
    private ProjectMember projectMember=new ProjectMember();
    private ProjectMemberRole projectMemberRole=new ProjectMemberRole();

    public Login getSession() {
        return session;
    }

    public void setSession(Login session) {
        this.session = session;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNavigation() {
        return navigation;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }
        
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectMember getProjectMember() {
        return projectMember;
    }

    public void setProjectMember(ProjectMember projectMember) {
        this.projectMember = projectMember;
    }

    public ProjectMemberRole getProjectMemberRole() {
        return projectMemberRole;
    }

    public void setProjectMemberRole(ProjectMemberRole projectMemberRole) {
        this.projectMemberRole = projectMemberRole;
    }
     
    /*
    Method to control sessions
    ==========================
    */
    private String sessionExpiration()
    {
        return "login";
    }
    /*
    List of projects to be displayed on the main page
    =================================================
    */
    public List<Project> projects(String user)
    {
        List<Project> lst=new ArrayList<>();
        try {
            
            DBConnection conn=new DBConnection();
            ResultSet result=conn.Data_Source("select scrum_project.id_project, scrum_project.project_name from scrum_project INNER JOIN project_member_role ON scrum_project.id_project=project_member_role.id_project where account='" + user + "'");
            while (result.next())
            {
                lst.add(new Project(
                        result.getString("id_project"),
                        result.getString("project_name")
                        )
                );
            }
            return lst;
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            return lst;
        }
    }
    
    /*
    Save Project and Project Owner
    ==============================
    */
    public String saveProject()
    {   
        if(!session.getUser().isEmpty())
        {
            try {
                ProjectMemberRole pjmRole=new ProjectMemberRole();
                projectMember.setAccount(session.getUser());
                projectMemberRole.setMember(projectMember);
                pjmRole.saveNewProject(projectMemberRole);
                message="Enregistrement effectué avec succès";
                navigation="main";
                return "ConfirmMsg";
            } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
                message=ex.getMessage();
                navigation="project";
                return "ConfirmMsg";
            }
        }
        else
        {
            return "login";
        }
    }
    
    /*
    Save Project member
    ===================
    */
    public String saveProjectMember()
    { 
        if(!session.getUser().isEmpty())
        {
            try {
                ProjectMember member=new ProjectMember();
                member.save(projectMember);
                message="Enregistrement effectué avec succès";
                navigation="login";
                return "ConfirmMsg";
            } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
                message=ex.getMessage();
                navigation="newAccount";
                return "ConfirmMsg";
            }
        }
        else
        {
            return "login";
        }
    }
    
    public String forwardPage()
    {
        return navigation;
    }
    
    public DataCollection() {
    }
    
}
