package com.ftms.ftmsapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


@Entity
@Table(name="recovery_codes")
public class RecoveryCode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    private Date expiredAt;

    private String code;

    private boolean used;

    private Long userID;

    public RecoveryCode(Date createdAt, Date expiredAt, String code, Long userID) {
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.code = code;
        this.userID = userID;
        this.used = false;
    }

    public RecoveryCode() {}

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public String getCode() {
        return code;
    }

    public boolean getUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Long getUserID() {
        return userID;
    }
}
