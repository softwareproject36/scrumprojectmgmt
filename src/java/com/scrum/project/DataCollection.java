
package com.scrum.project;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.json.simple.parser.ParseException;
import org.primefaces.event.SelectEvent;
import javax.faces.component.UIInput;
import org.primefaces.component.inputtext.InputText;

@ManagedBean
@RequestScoped
public class DataCollection {
    @ManagedProperty(value="#{login}")
    Login session;
    
    private String message;
    private String navigation;
    private String idProject;
    
    @ManagedProperty(value="#{param.pIdProj}")
    private String pIdProj;
    
    
    //Project Data
    private Project project=new Project();
    private ProjectMember projectMember=new ProjectMember();
    private ProjectMemberRole projectMemberRole=new ProjectMemberRole();
    private ProductBacklog productBacklog=new ProductBacklog();
    
    //Input Texts for binding project id,role and member account
    //==========================================================
    private UIInput uiProject=new UIInput();
    private UIInput uiRole=new UIInput();
    private UIInput uiMember=new UIInput();
    
    private InputText txtIdProject=new InputText();
    
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

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    public String getpIdProj() {
        return pIdProj;
    }

    public void setpIdProj(String pIdProj) {
        this.pIdProj = pIdProj;
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

    public ProductBacklog getProductBacklog() {
        return productBacklog;
    }

    public void setProductBacklog(ProductBacklog productBacklog) {
        this.productBacklog = productBacklog;
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

    public InputText getTxtIdProject() {
        return txtIdProject;
    }

    public void setTxtIdProject(InputText txtIdProject) {
        this.txtIdProject = txtIdProject;
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
    
    //Navigation to Product Backlog page
    //=================================
    public String productBacklog()
    {
        return "productBacklog";
    }
    
    //Save Product Backlog
    //====================    
    public String saveProductBacklog()
    { 
        Date dt=new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        
        if(!session.getUser().isEmpty())
        {
            try {
                productBacklog.getProject().setIdProj(txtIdProject.getValue().toString());
                productBacklog.setUserid(session.getUser());
                ProductBacklog prodBl=new ProductBacklog();
                prodBl.save(productBacklog);
                message="Enregistrement effectué avec succès";
                /*message="INSERT INTO product_backlog_item("
                        + "id_project, "
                        + "title, "
                        + "type_of_user, "
                        + "user_story, "
                        + "story_goal, "
                        + "item_priority, "
                        + "estimation, "
                        + "acceptance, "
                        + "domain, "
                        + "userid, "
                        + "saving_date) "
                        + "VALUES ('"
                        + productBacklog.getProject().getIdProj() + "','"
                        + productBacklog.getTitle() + "','"
                        + productBacklog.getType_of_user() + "','"
                        + productBacklog.getUser_story() + "','"
                        + productBacklog.getStory_goal() + "',"
                        + productBacklog.getItem_priority() + ","
                        + productBacklog.getEstimation() + ",'"
                        + productBacklog.getAcceptance() + "','"
                        + productBacklog.getDomain() + "','"
                        + productBacklog.getUserid() + "','"
                        + ft.format(dt) + "'"
                        + ")" ;*/
                navigation="main";
                return "ConfirmMsg";
            } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            //}catch(Exception ex){
                message=ex.getMessage();
                //message="Erreur";
                navigation="main";
                return "ConfirmMsg";
            }
        }
        else
        {
            return "login";
        }
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
    
    /*
    LIST OF MEMBERS OF A SPECIFIC PROJECT 
    =====================================
    */
    public List<ProjectMember> listProjectMembers(String project_id)
    {
        List<ProjectMember> lst=new ArrayList<>();
        try {
            
            DBConnection conn=new DBConnection();
            ResultSet result=conn.Data_Source("SELECT " +
                                    "project_member.account," +
                                    "project_member.name || ', ' || project_member_role.scr_role AS name," +
                                    "project_member.initials," +
                                    "project_member.addres," +
                                    "project_member.tel," +
                                    "project_member.email," +
                                    "project_member.category " +
                                    "FROM scrum_project " +
                                    "INNER JOIN project_member_role ON scrum_project.id_project=project_member_role.id_project " +
                                    "INNER JOIN project_member ON project_member_role.account=project_member.account " +
                                    "WHERE project_member_role.id_project='" + project_id + "' " +
                                    "AND project_member_role.scr_role != 'Project owner' " +
                                    "ORDER BY name"
                                );
            while (result.next())
            {
                lst.add(new ProjectMember(
                        result.getString("account"),
                        result.getString("name"),
                        result.getString("initials"),
                        result.getString("addres"),
                        result.getString("tel"),
                        result.getString("email"),
                        result.getString("category")
                    )
                );
                
            }
            return lst;
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            return lst;
        }
    }
    
    //LIST OF USER STORY DOMAINS
    //==========================
    public List<String> listDomains()
    {
        List<String> lst=new ArrayList<>();
        try {
            
            DBConnection conn=new DBConnection();
            ResultSet result=conn.Data_Source("SELECT '' AS domain UNION SELECT domain FROM backlog_item_domain ORDER BY domain");
            while (result.next())
            {
                lst.add(result.getString("domain"));
            }
            return lst;
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            return lst;
        }
    }
    
    public String forwardPage()
    {
        return navigation;
    }
    
    public DataCollection() {
    }
    
}
