package com.ftms.ftmsapi.payloadTest;

import com.ftms.ftmsapi.payload.JwtAuthenticationResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JwtAuthenticationResponseTest {
    @Test
    public void testGetAccessToken() {
        JwtAuthenticationResponse testResponse = new JwtAuthenticationResponse("Token");
        assertEquals(testResponse.getAccessToken(), "Token");
    }

    @Test
    public void testSetAccessToken() {
        JwtAuthenticationResponse testResponse = new JwtAuthenticationResponse("Token");
        testResponse.setAccessToken("Other Token");
        assertEquals(testResponse.getAccessToken(), "Other Token");

    }

    @Test
    public void testGetTokenType() {
        JwtAuthenticationResponse testResponse = new JwtAuthenticationResponse("Token");
        assertEquals(testResponse.getTokenType(), "Bearer");
    }

    @Test
    public void testSetTokenType() {
        JwtAuthenticationResponse testResponse = new JwtAuthenticationResponse("Token");
        testResponse.setTokenType("New Bearer");
        assertEquals(testResponse.getTokenType(), "New Bearer");
    }
}
