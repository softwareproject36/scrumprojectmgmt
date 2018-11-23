/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrum.project;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author JeanJacques
 */
@ManagedBean
@RequestScoped
public class Project {
private int ID;
private String projectName;
private Date Start_Date;
private Date End_Date;
private String Client;
private String ProdOwn;
    public Project() {
    }
 public Project(int idy,String projectnamea,Date Startdate, Date EndDate,String Cliente,String ProdOwner) {
     this.ID=idy;
     this.projectName=projectnamea;
     this.Start_Date=Startdate;
     this.End_Date=EndDate;
     this.Client=Cliente;
     this.ProdOwn=ProdOwner;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStart_Date() {
        return Start_Date;
    }

    public void setStart_Date(Date Start_Date) {
        this.Start_Date = Start_Date;
    }

    public Date getEnd_Date() {
        return End_Date;
    }

    public void setEnd_Date(Date End_Date) {
        this.End_Date = End_Date;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String Client) {
        this.Client = Client;
    }

    public String getProdOwn() {
        return ProdOwn;
    }

    public void setProdOwn(String ProdOwn) {
        this.ProdOwn = ProdOwn;
    }
    
}
