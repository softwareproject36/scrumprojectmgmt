
package com.scrum.project;

import java.io.IOException;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import org.json.simple.parser.ParseException;

@ManagedBean
public class MemberRoleBean {
    
    private String message;
    private String navigation;
    private String account;
    private String id_project;
    private String scr_role;
    
    public MemberRoleBean() {
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
    
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getId_project() {
        return id_project;
    }

    public void setId_project(String id_project) {
        this.id_project = id_project;
    }

    public String getScr_role() {
        return scr_role;
    }

    public void setScr_role(String scr_role) {
        this.scr_role = scr_role;
    }
 
    public String role(String userId,String projectId){
        String rl="";
        DBConnection conn=new DBConnection();
        try { 
            rl=conn.Show_Data("SELECT scr_role FROM project_member_role WHERE  account='" + userId +"' AND id_project='" + projectId +"'", 
                    "scr_role", 1);
            return rl;
            
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {            
            message=ex.getMessage();
            return rl;
        }        
    }
    
    public String selectMember()
    {
        return "selectMember";
    }
    
    public String login()
    {
        return "login";
    }
    
    public String save()
    {
        DBConnection conn=new DBConnection();
        try { 
            conn.Execute_Query("INSERT INTO project_member_role(account, id_project, scr_role) VALUES('"
                + account + "','"
                + id_project + "','"
                + scr_role + "')" 
            );
            //return "main";
            message="Enregistrement effectué avec succès";
            navigation="main";
            return "ConfirmMsg";
            
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {            
            message=ex.getMessage();
            navigation="main";
            return "ConfirmMsg";
        } 
    }
}
