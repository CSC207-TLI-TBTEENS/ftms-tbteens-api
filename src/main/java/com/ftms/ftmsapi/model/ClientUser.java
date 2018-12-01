package com.ftms.ftmsapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue( value="client" )
public class ClientUser extends User {

    private Company company;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    public Company getCompany(){
        return company;
    }

    public void setCompany(Company company){
        this.company = company;
    }

    public ClientUser () {
        super();
    }

    public ClientUser(String fname, String lname, String email, String number, String role, Company company){
        super(fname, lname, email, number, role);
        this.company = company;
    }
}
