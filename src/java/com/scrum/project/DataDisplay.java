
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
public class DataDisplay {
    
    @ManagedProperty(value="#{param.pIdProj}")
    private String pIdProj;
    
    public DataDisplay() {
    }

    public String getpIdProj() {
        return pIdProj;
    }

    public void setpIdProj(String pIdProj) {
        this.pIdProj = pIdProj;
    }
        
    public String productBacklog()
    {
        return "viewProductBacklog";
    }
    
    /*
    List of Product Backlog
    =======================
    */
    public List<ProductBacklog> lstProductBacklog()
    {
        List<ProductBacklog> lst=new ArrayList<>();
        try {
            
            DBConnection conn=new DBConnection();
            ResultSet result=conn.Data_Source("SELECT " +
                                                "id_project, " +
                                                "title, " +
                                                "type_of_user, " +
                                                "user_story, " +
                                                "story_goal, " +
                                                "item_priority, " +
                                                "estimation, " +
                                                "acceptance, " +
                                                "domain, " +
                                                "userid, " +
                                                "saving_date " +
                                                "FROM product_backlog_item " +
                                                "WHERE id_project='" + this.pIdProj + "'");
            while (result.next())
            {
                lst.add(new ProductBacklog(
                        result.getString("title"),
                        result.getString("type_of_user"),
                        result.getString("user_story"),
                        result.getString("story_goal"),
                        Integer.valueOf(result.getString("item_priority")),
                        Float.valueOf(result.getString("estimation")),
                        result.getString("acceptance"),
                        result.getString("domain"),
                        result.getString("userid")
                ));
                
                /*lst.add(new Project(
                        result.getString("id_project"),
                        result.getString("project_name")
                        )
                );*/
            }
            return lst;
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            lst.add(new ProductBacklog(ex.getMessage(),ex.getMessage(),"-","-",0,0,"-","-","-"));
            return lst;
        }
    }
}
