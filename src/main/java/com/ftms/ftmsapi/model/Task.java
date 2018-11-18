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

//    @OneToOne(fetch=FetchType.LAZY)
//    @MapsId
//    private Employee employee;
//
//    @OneToOne(fetch=FetchType.LAZY)
//    @MapsId
//    private Job job;
//
//    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
//    private Set<Time> times;

    private String description;

    public Long getId() {
        return id;
    }

    // GETTERS/SETTERS
//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
//
//    public Job getJob() {
//        return job;
//    }
//
//    public void setJob(Job job) {
//        this.job = job;
//    }
//
//    public Set<Time> getTimes() {
//        return times;
//    }
//
//    public void setTimes(Set<Time> times) {
//        this.times = times;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
