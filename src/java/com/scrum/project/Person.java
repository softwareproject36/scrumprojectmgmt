
package com.scrum.project;

public class Person {

    private String idpers;
    private String adress;
    private String email;

    public Person() {
        
    }
    
    public Person(String idpers)
    {
        this.idpers=idpers;
    }
    
    public Person(String idpers,String adress,String email) {
        this.idpers=idpers;
        this.adress=adress;
        this.email=email;
    }

    public String getIdpers() {
        return idpers;
    }

    public void setIdpers(String idpers) {
        this.idpers = idpers;
    }
    
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
