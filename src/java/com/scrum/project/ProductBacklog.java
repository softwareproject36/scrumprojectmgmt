
package com.scrum.project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.parser.ParseException;

public class ProductBacklog {
    private Project project=new Project();
    private String title;
    private String type_of_user;
    private String user_story;
    private String story_goal;
    private int item_priority;
    private float estimation;
    private String acceptance;
    private String domain;
    private String userid;
    private Date saving_date=new Date();

    public ProductBacklog() {
    }

    public ProductBacklog(String title, String type_of_user, String user_story, String story_goal, int item_priority, float estimation, String acceptance, String domain, String userid) {
        this.title = title;
        this.type_of_user = type_of_user;
        this.user_story = user_story;
        this.story_goal = story_goal;
        this.item_priority = item_priority;
        this.estimation = estimation;
        this.acceptance = acceptance;
        this.domain = domain;
        this.userid = userid;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType_of_user() {
        return type_of_user;
    }

    public void setType_of_user(String type_of_user) {
        this.type_of_user = type_of_user;
    }

    public String getUser_story() {
        return user_story;
    }

    public void setUser_story(String user_story) {
        this.user_story = user_story;
    }

    public String getStory_goal() {
        return story_goal;
    }

    public void setStory_goal(String story_goal) {
        this.story_goal = story_goal;
    }

    public int getItem_priority() {
        return item_priority;
    }

    public void setItem_priority(int item_priority) {
        this.item_priority = item_priority;
    }

    public float getEstimation() {
        return estimation;
    }

    public void setEstimation(float estimation) {
        this.estimation = estimation;
    }

    public String getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(String acceptance) {
        this.acceptance = acceptance;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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
    
    //Save Product Backlog
    //====================
    public void save(ProductBacklog pblog) throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, ParseException
    {
        Date dt=new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        
        DBConnection conn=new DBConnection();
        conn.Execute_Query("INSERT INTO product_backlog_item("
                        + "pbl_id, "
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
                        + "SELECT "
                        + "(SELECT id FROM (SELECT uuid_in((md5((random())::text))::cstring) as id) t WHERE id NOT IN (SELECT pbl_id FROM product_backlog_item)),'"
                        + pblog.project.getIdProj() + "','"
                        + pblog.title + "','"
                        + pblog.type_of_user + "','"
                        + pblog.user_story + "','"
                        + pblog.story_goal + "',"
                        + pblog.item_priority + ","
                        + pblog.estimation + ",'"
                        + pblog.acceptance + "','"
                        + pblog.domain + "','"
                        + pblog.userid + "','"
                        + ft.format(dt) + "'"
            );
    }
}
