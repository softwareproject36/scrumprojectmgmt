
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
import org.primefaces.event.SelectEvent;
import javax.faces.component.UIInput;

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
    
    //Input Texts for binding project id,role and member account
    //==========================================================
    private UIInput uiProject=new UIInput();
    private UIInput uiRole=new UIInput();
    private UIInput uiMember=new UIInput();
    
    public void onload()
    {
        
    }

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

    public UIInput getUiProject() {
        return uiProject;
    }

    public void setUiProject(UIInput uiProject) {
        this.uiProject = uiProject;
    }

    public UIInput getUiRole() {
        return uiRole;
    }

    public void setUiRole(UIInput uiRole) {
        this.uiRole = uiRole;
    }

    public UIInput getUiMember() {
        return uiMember;
    }

    public void setUiMember(UIInput uiMember) {
        this.uiMember = uiMember;
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
    
    //Save Project Member Role
    //========================
    public String saveProjectMemberrole()
    { 
        if(!session.getUser().isEmpty())
        {
            try {
                DBConnection conn=new DBConnection();
                /*conn.Execute_Query("INSERT INTO project_member_role(account, id_project, scr_role) VALUES('"
                        + uiMember.getValue().toString() + "','"
                        + uiProject.getValue().toString() + "','"
                        + uiRole.getValue().toString() + "')" 
                    );*/
                String proj=projectMemberRole.getProject().getIdProj();
                String rl=projectMemberRole.getRole();
                String mbre=projectMemberRole.getMember().getAccount();
                conn.Execute_Query("INSERT INTO project_member_role(account, id_project, scr_role) VALUES('"
                        + mbre + "','"
                        + proj + "','"
                        + rl + "')" 
                    );
                message="Enregistrement effectué avec succès";
                navigation="main";
                return "ConfirmMsg";
            } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
                message=ex.getMessage();
                //message= "project= " + uiProject.getValue().toString() + "; role= " + uiRole.getValue().toString() + "; member= " + uiMember.getValue().toString();
                navigation="main";
                return "ConfirmMsg";
            }
        }
        else
        {
            return "login";
        } 
        
        /*message= "project= " + projectMemberRole.getProject().getIdProj() + "; role= " + projectMemberRole.getRole() + "; member= " + projectMemberRole.getMember().getAccount();
        navigation="main";
        //message=uiProject.getValue().toString();
        return "ConfirmMsg";*/
    }
    
    /*
    Select 
    ===================
    */
    public void onRowSelect(SelectEvent event)
    {
        ProjectMemberRole mbr=(ProjectMemberRole) event.getObject();
        this.projectMemberRole=mbr;
    }
    
    public String selectMember()
    {
        return "selectMember";
    }
    
    public String forwardPage()
    {
        return navigation;
    }
    
    public DataCollection() {
    }
    
}
