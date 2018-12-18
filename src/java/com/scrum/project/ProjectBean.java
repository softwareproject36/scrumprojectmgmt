
package com.scrum.project;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.json.simple.parser.ParseException;
//import javax.faces.bean.NoneScoped;

@ManagedBean
//@NoneScoped
public class ProjectBean {

    private List<Project> projects;
    private String errorMsg;
    
    @ManagedProperty(value="#{login}")
    Login session;
    
    @ManagedProperty(value="#{param.pUserId}")
    private String pUserId;
    
    @ManagedProperty(value="#{param.userrole}")
    private String userrole;
    
    @ManagedProperty(value="#{param.pIdProj}")
    private String pIdProj;
    
        
    public ProjectBean() {
        /*
        projects=new ArrayList<>();
        try {
            
            DBConnection conn=new DBConnection();
            ResultSet result=conn.Data_Source(
                            "SELECT " +
                            "scrum_project.id_project," +
                            "scrum_project.project_name," +
                            "project_member_role.scr_role," +
                            "project_member.account," +
                            "project_member.name," +
                            "project_member.initials," +
                            "project_member.addres," +
                            "project_member.tel," +
                            "project_member.email," +
                            "project_member.category " +
                            "FROM scrum_project " +
                            "INNER JOIN project_member_role ON scrum_project.id_project=project_member_role.id_project " +
                            "INNER JOIN project_member ON project_member_role.account=project_member.account " +
                            "WHERE project_member.account='" + session.getUser() + "' " +
                            "AND project_member_role.scr_role='" + pIdProj + "'"
                        );
            while (result.next())
            {
                projects.add(new Project(result.getString("id_project"),result.getString("project_name"),result.getString("name")));
                
            }
        
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            errorMsg=ex.getMessage();
        }*/
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Login getSession() {
        return session;
    }

    public void setSession(Login session) {
        this.session = session;
    }
        
    public String getpUserId() {
        return pUserId;
    }

    public void setpUserId(String pUserId) {
        this.pUserId = pUserId;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }
    
    public String getpIdProj() {
        return pIdProj;
    }

    public void setpIdProj(String pIdProj) {
        this.pIdProj = pIdProj;
    }
    
    public List<Project> lstProjects()
    {
        List<Project> lst=new ArrayList<>();
        try {
            
            DBConnection conn=new DBConnection();
            ResultSet result=conn.Data_Source(
                            "SELECT " +
                            "scrum_project.id_project," +
                            "scrum_project.project_name," +
                            "project_member_role.scr_role," +
                            "project_member.account," +
                            "project_member.name," +
                            "project_member.initials," +
                            "project_member.addres," +
                            "project_member.tel," +
                            "project_member.email," +
                            "project_member.category " +
                            "FROM scrum_project " +
                            "INNER JOIN project_member_role ON scrum_project.id_project=project_member_role.id_project " +
                            "INNER JOIN project_member ON project_member_role.account=project_member.account " +
                            "WHERE project_member.account='" + session.getUser() + "'" 
                        );
            while (result.next())
            {
                lst.add(new Project(result.getString("id_project"),result.getString("project_name"),result.getString("name")));
                
            }
            return lst;
            
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            errorMsg=ex.getMessage();
            return lst;
        }
    }
    
    public String selectProject()
    {
        return "selectProject";
    }
    
    public String selectMember()
    {
        return "selectMember";
    }
    
    public String selectMemberRole()
    {
        return "selectMemberRole";
    }
}
