package com.ftms.ftmsapi.model;

import java.io.Serializable;

import javax.persistence.*;

//For the timesheet creation in jobassignment do not touch
@Entity
public class Selection implements Serializable{
    // INSTANCE FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private User employee;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    /**
     * @return the job
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    public Job getJob() {
        return job;
    }

    /**
     * @return the employee
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    public User getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    public void setEmployee(User employee) {
        this.employee = employee;
    }

    /**
     * @param job the job to set
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    public void setJob(Job job) {
        this.job = job;
    }
}