
package com.scrum.project;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Navigation {
    
    @ManagedProperty(value="#{param.member}")
    private String member;
    
    @ManagedProperty(value="#{param.userrole}")
    private String userrole;
    
    @ManagedProperty(value="#{param.pIdProj}")
    private String pIdProj;
    
    @ManagedProperty(value="#{param.pMemberRole}")
    private String pMemberRole;

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
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

    public String getpMemberRole() {
        return pMemberRole;
    }

    public void setpMemberRole(String pMemberRole) {
        this.pMemberRole = pMemberRole;
    }
    
                
    public String project()
    {
        return "project";
    }
    
    public String individual()
    {
        return "individual";
    }
    
    public String company()
    {
        return "company";
    }
    
    public String companyType()
    {
        return "companytype";
    }
    
    public String telephone()
    {
        return "telephones";
    }
    
    public String selectProject()
    {
        return "selectProject";
    }
    
    public String selectMember()
    {
        return "selectMember";
    }
    
    public Navigation() {
    }
    
}
