package com.ftms.ftmsapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name="time")
public class Time implements  Serializable {

    // INSTANCE FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Long startTime;

    // End times need to have the option to blank as they get added later.
    private Long endTime;

//    @ManyToOne
//    @JoinColumn(name="task_id")
//    private Task task;

    // GETTERS/SETTERS
    public Long getId(){
        return id;
    }

    public Long getStartTime(){
        return startTime;
    }

    public void setStartTime(Long startTime){
        this.startTime = startTime;
    }

    public Long getEndTime(){
        return this.endTime;
    }

    public void setEndTime(Long endTime){
        this.endTime = endTime;
    }
}
