
package com.scrum.project;

public class Person {

private String idps;
private String adress;

private String email;

    public Person() {
    }
 public Person(String aidps,String aadress,String aemail) {
     this.idps=aidps;
     this.adress=aadress;
     this.email=aemail;
    }
    public String getIdps() {
        return idps;
    }

    public void setIdps(String idps) {
        this.idps = idps;
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
