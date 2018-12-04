package com.ftms.ftmsapi.modelTest;

import org.junit.Test;
import com.ftms.ftmsapi.model.RecoveryCode;
import java.util.Date;

import static org.junit.Assert.*;

public class RecoveryCodeTest {
    Date d1 = new Date();
    Date d2 = new Date();

    RecoveryCode rc = new RecoveryCode(d1, d2, "Julie", 100L);

    @Test
    public void testRecoveryCreatedAt() {
        assertEquals(rc.getCreatedAt(), d1);
    }

    @Test
    public void testRecoveryExpiredAt() {
        assertEquals(rc.getExpiredAt(), d2);
    }

    @Test
    public void testRecoveryGetCode() {
        assertEquals(rc.getCode(), "Julie");
    }

    @Test
    public void testRecoveryUsed() {
        rc.setUsed(true);
        assertEquals(rc.getUsed(), true);
    }

    @Test
    public void testRecoveryUserID() {
        assertEquals(rc.getUserID(), 100L, 100L);
    }

}