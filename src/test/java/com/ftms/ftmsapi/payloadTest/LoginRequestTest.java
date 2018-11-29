package com.ftms.ftmsapi.payloadTest;

import com.ftms.ftmsapi.payload.LoginRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginRequestTest {

    @Test
    public void testEmail() {
        LoginRequest newRequest = new LoginRequest();
        newRequest.setEmail("eceyucer@gmail.com");
        assertEquals(newRequest.getEmail(), "eceyucer@gmail.com");
    }


    @Test
    public void testPassword() {
        LoginRequest newRequest = new LoginRequest();
        newRequest.setPassword("abcde1234");
        assertEquals(newRequest.getPassword(), "abcde1234");
    }


}