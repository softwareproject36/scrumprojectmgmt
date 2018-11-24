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
public class Telephone {

   private String idPers;
   private String tel_number;

    public Telephone(String idPers, String tel_number) {
        this.idPers = idPers;
        this.tel_number = tel_number;
    }
    public Telephone() {
    }

    public String getIdPers() {
        return idPers;
    }

    public String getTel_number() {
        return tel_number;
    }
    
}
