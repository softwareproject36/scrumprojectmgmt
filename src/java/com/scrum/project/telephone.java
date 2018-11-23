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
public class telephone {
private String idPers;
private String tel_number;

    public String getIdPers() {
        return idPers;
    }

    public void setIdPers(String idPers) {
        this.idPers = idPers;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    /**
     * Creates a new instance of telephone
     */
    public telephone() {
    }
    
}
