
package com.scrum.project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.simple.parser.ParseException;

public class ProjectMemberRole {
    private ProjectMember member=new ProjectMember();
    private Project project=new Project();
    private String role;
    private String userid;
    private Date saving_date=new Date();
    
    public ProjectMemberRole()
    {
        
    }
    
    public ProjectMemberRole(ProjectMember member,Project project,String role)
    {
        this.member=member;
        this.project=project;
        this.role=role;
    }

    public ProjectMember getMember() {
        return member;
    }

    public void setMember(ProjectMember member) {
        this.member = member;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getSaving_date() {
        return saving_date;
    }

    public void setSaving_date(Date saving_date) {
        this.saving_date = saving_date;
    }
        
    public void saveNewProject(ProjectMemberRole projectMemberRole) throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException
    {
        DBConnection conn=new DBConnection();
        String idProj=conn.Show_Data("select pjID from (select substring(CAST(uuid_in((md5((random())::text))::cstring) as varchar(50)),1,8) as pjID) t where pjID not in(select id_project from scrum_project)", "pjID", 1);
        conn.Execute_Query("BEGIN; "
                + "INSERT INTO scrum_project(id_project,project_name,userid) SELECT "
                + "'" + idProj + "',"
                + "'" + projectMemberRole.project.getProjectName().replace("'", "''") + "',"
                + "'" + projectMemberRole.member.getAccount() + "'"
                + "; "
                + "INSERT INTO project_member_role("
                + "account,"
                + "id_project,"
                + "scr_role"
                + ") "
                + "VALUES("
                + "'" + projectMemberRole.member.getAccount() + "',"
                + "'" + idProj + "',"
                + "'Project owner'"
                + "); "
                + "COMMIT;"
        );
    }
    
    public void saveMemberRole(ProjectMemberRole projectMemberRole) throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException
    {
        DBConnection conn=new DBConnection();
        conn.Execute_Query("INSERT INTO project_member_role(account, id_project, scr_role) VALUES('"
                + projectMemberRole.member.getAccount() + "','"
                + projectMemberRole.project.getIdProj() + "','"
                + projectMemberRole.role + "')" 
            );
    }
    
    /*
    List of Project Member Role for DataTable
    =========================================
    */
    public List<ProjectMemberRole> ListProjects(String user,String role)
    {
        List<ProjectMemberRole> lst=new ArrayList<>();
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
                            "WHERE project_member.account='" + user + "' " +
                            "AND project_member_role.scr_role='" + role + "'"
                        );
            while (result.next())
            {
                ProjectMember pjm=new ProjectMember();
                Project pj=new Project();
                
                pjm.setAccount(result.getString("account"));
                pjm.setName(result.getString("name"));
                pjm.setInitials(result.getString("initials"));
                pjm.setAddress(result.getString("addres"));
                pjm.setTel(result.getString("tel"));
                pjm.setEmail(result.getString("email"));
                pjm.setCategory(result.getString("category"));
                pj.setIdProj(result.getString("id_project"));
                pj.setProjectName(result.getString("project_name"));
                String rl=result.getString("scr_role");
                
                ProjectMemberRole pjmRole=new ProjectMemberRole(pjm,pj,rl);
                
                lst.add(pjmRole);
            }
            return lst;
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            return lst;
        }
    }
}
