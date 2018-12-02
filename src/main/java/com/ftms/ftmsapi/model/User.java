package com.ftms.ftmsapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NaturalId
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String number;

    private boolean active;

    @JsonIgnore
    private String password;

    @NotBlank
    private String role;

    public User() {
        this.active = false;
    }

    public User(String fname, String lname, String email, String number, String role) {
        this.firstname = fname;
        this.lastname = lname;
        this.email = email;
        this.number = number;
        this.role = role;
        this.active = false;
    }

    /**
     * Getter for ID.
     *
     * @return The id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter for first name.
     *
     * @return The first name.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Setter for first name.
     *
     * @param firstname The new first name.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Getter for last name.
     *
     * @return The last name.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Setter for last name.
     *
     * @param lastname The new last name.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Getter for email.
     *
     * @return The email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email.
     *
     * @param email The new email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for password.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password.
     *
     * @param password The new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
