package com.ftms.ftmsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue( value="client" )
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClientUser extends User {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
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
