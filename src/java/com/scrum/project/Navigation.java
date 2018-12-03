
package com.scrum.project;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Navigation {

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
    
    public Navigation() {
    }
    
}
