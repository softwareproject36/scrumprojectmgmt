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
public class Individual extends Person {

    private String idPers;
    private String firstName;
    private String lastName;
    private String middleName;

    public String getIdPers() {
        return idPers;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    /*public Individual(String idPers, String firstName, String lastName, String middleName, String idPers, String address, String email) {
        super(idPers, address, email);
        this.idPers = idPers;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }*/

    public Individual(String idPers, String firstName, String lastName, String middleName) {
        this.idPers = idPers;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }
    
    public Individual() {
    }
    
}
