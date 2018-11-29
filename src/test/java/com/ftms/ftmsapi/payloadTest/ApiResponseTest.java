package com.ftms.ftmsapi.payloadTest;

import com.ftms.ftmsapi.payload.ApiResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApiResponseTest {

    @Test
    public void testGetSuccess() {
        ApiResponse testResponse = new ApiResponse(true, "message");
        assertEquals(testResponse.getSuccess(), true);
    }

    @Test
    public void testSetSuccess() {
        ApiResponse testResponse = new ApiResponse(true, "message");
        testResponse.setSuccess(false);
        assertEquals(testResponse.getSuccess(), false);

    }

    @Test
    public void testGetMessage() {
        ApiResponse testResponse = new ApiResponse(true, "message");
        assertEquals(testResponse.getMessage(), "message");
    }

    @Test
    public void testSetMessage() {
        ApiResponse testResponse = new ApiResponse(true, "message");
        testResponse.setMessage("new message");
        assertEquals(testResponse.getMessage(), "new message");
    }
}
