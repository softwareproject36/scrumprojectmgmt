
package com.scrum.project;

public class Company {
private String idPers;
private String company_name;
private String initials;
private int regNember;
private String compType;
    public Company() {
        
    }
public Company(String pidps,String pcompName, String initial,int regnum,String comptype) {
        this.idPers=pidps;
        this.company_name=pcompName;
        this.initials=initial;
        this.regNember=regnum;
        this.compType=comptype;
    }

    public String getIdPers() {
        return idPers;
    }

    public void setIdPers(String idPers) {
        this.idPers = idPers;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public int getRegNember() {
        return regNember;
    }

    public void setRegNember(int regNember) {
        this.regNember = regNember;
    }

    public String getCompType() {
        return compType;
    }

    public void setCompType(String compType) {
        this.compType = compType;
    }
   
}
