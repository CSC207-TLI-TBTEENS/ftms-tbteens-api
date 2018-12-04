package com.ftms.ftmsapi.payloadTest;

import com.ftms.ftmsapi.payload.SignUpRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SignUpRequestTest {
    @Test
    public void testEmail() {
        SignUpRequest newRequest = new SignUpRequest();
        newRequest.setId("22");
        assertEquals(newRequest.getId(), "22");
    }

    @Test
    public void testPassword() {
        SignUpRequest newRequest = new SignUpRequest();
        newRequest.setPassword("abcde1234");
        assertEquals(newRequest.getPassword(), "abcde1234");

    }


}

