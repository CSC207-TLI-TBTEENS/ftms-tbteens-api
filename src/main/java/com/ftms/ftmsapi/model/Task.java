package com.ftms.ftmsapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="task")
public class Task implements Serializable{
    // INSTANCE FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private User employee;

    private Job job;

    private String description;

    public Long getId() {
        return id;
    }

    // GETTERS/SETTERS

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
