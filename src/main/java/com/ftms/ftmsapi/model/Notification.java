package com.ftms.ftmsapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="notifications")
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long notificationId;

    private String message;

    private Date createdAt;

    private boolean isRead;

    private String type;

    @ManyToOne
    private Job job;

    @ManyToOne
    private User user;

    public Notification(){}

    public Notification(String message, Date createdAt, User user, String type, Job job){
        this.message = message;
        this.createdAt = createdAt;
        this.user = user;
        this.type = type;
        this.job = job;
        this.isRead = false;
    }


    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() { return type; }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }
}
