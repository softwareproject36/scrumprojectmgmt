
package com.scrum.project;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.sql.SQLException;
import org.json.simple.parser.ParseException;

@ManagedBean
@RequestScoped
public class Login {

    private String user;
    private String password;
    private String message;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
        
    public String connect(){
        
        DBConnection conn=new DBConnection();
        try {            
            int nbre= Integer.valueOf(conn.Show_Data("select count(*) as nbre from sysUser "
                    + "where idPers='" + user.replace("'", "''") + "' "
                    + "and pswd='" + password.replace("'", "''") + "'", 
                    "nbre", 1));
            
            if(nbre==1)
                return "main";
            else
            {
                message="Nom ou Mot de passe incorrecte";
                return "login";
            }
            
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {            
            message=ex.getMessage();
            return "login";
        }        
    }
    public Login() {
    }
    
}
