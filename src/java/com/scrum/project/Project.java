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

private String Client;
private String ProdOwn;
    public Project() {
    }
 public Project(int idy,String projectnamea,String Cliente,String ProdOwner) {
     this.ID=idy;
     this.projectName=projectnamea;
     
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
