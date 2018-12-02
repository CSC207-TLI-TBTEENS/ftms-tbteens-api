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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User employee;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    private String description;

    // GETTERS/SETTERS

    /**
     * Getter for ID
     *
     * @return The ID of this.
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter for employee.
     *
     * @return The id.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    public User getEmployee() {
        return employee;
    }

    /**
     * Setter for employee.
     *
     * @param employee The employee to be changed.
     */
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
