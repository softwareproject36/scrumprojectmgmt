
package com.scrum.project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.ParseException;

public class ProjectMember {
    private String account;
    private String name;
    private String initials;
    private String address;
    private String tel;
    private String email;
    private String category;
    private String password;
    private String confirmPswd;
    
    public ProjectMember()
    {
        
    }
    
    public ProjectMember(String account,String name,String initials,String address,String tel,String email,String category)
    {
        this.account=account;
        this.name=name;
        this.initials=initials;
        this.address=address;
        this.tel=tel;
        this.email=email;
        this.category=category;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPswd() {
        return confirmPswd;
    }

    public void setConfirmPswd(String confirmPswd) {
        this.confirmPswd = confirmPswd;
    }
    
    /*
    List of Categories
    ==================
    */    
    public List<String> categories()
    {
        List<String> lst=new ArrayList<>();
        try {
            
            DBConnection conn=new DBConnection();
            ResultSet result=conn.Data_Source("select category from member_categorie order by category");
            while (result.next())
            {
                lst.add(result.getString("category"));
            }
            return lst;
        } catch (ClassNotFoundException | SQLException | IOException | ParseException ex) {
            return lst;
        }
    }
    
    /*
    Save Project Member
    ===================
    */
    public void save(ProjectMember member) throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException
    {
        DBConnection conn=new DBConnection();
        conn.Execute_Query("BEGIN; "
                + "INSERT INTO project_member("
                + "account,"
                + "name,"
                + "initials,"
                + "addres,"
                + "tel,"
                + "email,"
                + "category"
                + ") "
                + "VALUES("
                + "'" + member.account + "',"
                + "'" + member.name.replace("'", "''") + "',"
                + "'" + member.initials.replace("'", "''") + "',"
                + "'" + member.address.replace("'", "''") + "',"
                + "'" + member.tel.replace("'", "''") + "',"
                + "'" + member.email.replace("'", "''") + "',"
                + "'" + member.category.replace("'", "''") + "'"
                + "); " 
                + "INSERT INTO user_account("
                + "account,"
                + "pswd"
                + ") "
                + "VALUES("
                + "'" + member.account + "',"
                + "'" + member.password.replace("'","''") + "'"
                + "); "
                + "COMMIT;"
        );
    }
}
