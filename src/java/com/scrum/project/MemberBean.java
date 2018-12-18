
package com.scrum.project;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.json.simple.parser.ParseException;

@ManagedBean
public class MemberBean {

    private String errorMsg;
    
    @ManagedProperty(value="#{login}")
    Login session;
    
    @ManagedProperty(value="#{param.pUserId}")
    private String pUserId;
    
    @ManagedProperty(value="#{param.userrole}")
    private String userrole;
    
    @ManagedProperty(value="#{param.pIdProj}")
    private String pIdProj;

    public MemberBean() {
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
    
    
    public String selectMember()
    {
        return "selectMember";
    }
    
    /*
    LIST OF MEMBERS
    ===============
    */
    public List<ProjectMember> listMembers()
    {
        List<ProjectMember> lst=new ArrayList<>();
        try {
            
            DBConnection conn=new DBConnection();
            ResultSet result=conn.Data_Source(
                             "SELECT account, "
                                     + "name, "
                                     + "initials, "
                                     + "addres, "
                                     + "tel, "
                                     + "email, "
                                     + "category "
                                     + "FROM project_member "
                                     + "WHERE category='Individual'"       
                        );
            while (result.next())
            {
                //lst.add(new Project(result.getString("id_project"),result.getString("project_name"),result.getString("name")));
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
            errorMsg=ex.getMessage();
            return lst;
        }
    }
}
