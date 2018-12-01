package com.ftms.ftmsapi.payload;

import java.util.Date;

public class Time {
    private Date expiry;

    private Date end;

    public Time(Date expiry, Date end) {
        this.expiry = expiry;
        this.end = end;
    }

    public Date getExpiry() { return expiry; }

    public Date getEnd() { return end; }

    public boolean isValidTimeInterval() {
        Long expiryTime = expiry.getTime();
        Long endTime = expiry.getTime();

        long difference = expiryTime - endTime;

        return 0 <= difference && difference <= 3600000;
    }
}
