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
public class Individual {
private String idps;
private String name;
private String middlename;
private String nickname;
private String gender;
    
public Individual(String idpsa,String namea,String middlenamea,String nicknamea,String gendera) {
    this.idps=idpsa;
    this.name=namea;
    this.middlename=middlenamea;
    this.nickname=nicknamea;
    this.gender=gendera;
    }
    
    /**
     * Creates a new instance of Individual
     */
    public Individual() {
    }

    public String getIdps() {
        return idps;
    }

    public void setIdps(String idps) {
        this.idps = idps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
}
