
package com.scrum.project;

public class Individual {
private String idPers;
private String firstName;
private String lastName;
private String middleName;


    
public Individual(String idpsa,String namea,String middlenamea,String nicknamea,String gendera) {
    this.idPers=idpsa;
    this.firstName=namea;
    this.lastName=middlenamea;
    this.middleName=nicknamea;
    
    }
    
    /**
     * Creates a new instance of Individual
     */
    public Individual() {
    }

    public String getIdPers() {
        return idPers;
    }

    public void setIdPers(String idPers) {
        this.idPers = idPers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
