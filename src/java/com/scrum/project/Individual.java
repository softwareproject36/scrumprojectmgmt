
package com.scrum.project;

public class Individual extends Person{
    private String idPers;
    private String firstName;
    private String lastName;
    private String middleName;

    public Individual()
    {
        
    }
    
    public Individual(String idPers,String firstName,String lastName,String middleName) {
        this.idPers=idPers;
        this.firstName=firstName;
        this.lastName=lastName;
        this.middleName=middleName;
    
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
