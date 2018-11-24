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
public class Person {

private String idPers;
private String address;
private String email;

    public Person(String idPers, String address, String email) {
        this.idPers = idPers;
        this.address = address;
        this.email = email;
    }

    public Person() {
    }

    public String getIdPers() {
        return idPers;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
    
}
