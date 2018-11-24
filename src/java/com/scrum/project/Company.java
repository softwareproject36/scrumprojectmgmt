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
public class Company extends Person {
    
 private String idPErs;
    private String company_name;
    private String initials;
    private String regNumber;
    private String comtype;
    
    public Company(String idPErs, String company_name, String initials, String regNumber, String comtype, String idPers, String address, String email) {
        super(idPers, address, email);
        this.idPErs = idPErs;
        this.company_name = company_name;
        this.initials = initials;
        this.regNumber = regNumber;
        this.comtype = comtype;
    }

    public Company(String idPErs, String company_name, String initials, String regNumber, String comtype) {
        this.idPErs = idPErs;
        this.company_name = company_name;
        this.initials = initials;
        this.regNumber = regNumber;
        this.comtype = comtype;
    }

    public Company() {
    }
    
}
