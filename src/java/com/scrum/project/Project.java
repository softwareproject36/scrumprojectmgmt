/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrum.project;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Jr
 */
@ManagedBean
@RequestScoped
public class Project {

    private String idProject;
    private String projectName;
    private String ownerID;

    public Project(String idProject, String projectName, String ownerID) {
        this.idProject = idProject;
        this.projectName = projectName;
        this.ownerID = ownerID;
    }
   
    public Project() {
    }

    public String getIdProject() {
        return idProject;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getOwnerID() {
        return ownerID;
    }
    
}
