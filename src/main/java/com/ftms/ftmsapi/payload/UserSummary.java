package com.ftms.ftmsapi.payload;

import com.ftms.ftmsapi.model.Role;

import java.util.Set;

public class UserSummary {
    private Long id;
    private String email;
    private String fname;
    private String lname;
    private Set<Role> roles;

    public UserSummary(Long id, String email, String fname, String lname, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.roles = roles;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return fname;
    }

    public void setFirstname(String fname) {
        this.fname = fname;
    }

    public String getLastname() {
        return lname;
    }

    public void setLastname(String lname) {
        this.lname = lname;
    }

    public Set<Role> getRole() {
        return roles;
    }

    public void setRole(Set<Role> roles) {
        this.roles = roles;
    }
}
