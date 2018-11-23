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
 * @author JeanJacques
 */
@ManagedBean
@RequestScoped
public class Company {
private String idps;
private String compName;
private String RCCM;
    
    public Company() {
        
    }
public Company(String pidps,String pcompName,String pRccm) {
        this.idps=pidps;
        this.compName=pcompName;
        this.RCCM=pRccm;
    }
    public String getIdps() {
        return idps;
    }

    public void setIdps(String idps) {
        this.idps = idps;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getRCCM() {
        return RCCM;
    }

    public void setRCCM(String RCCM) {
        this.RCCM = RCCM;
    }
    
}
