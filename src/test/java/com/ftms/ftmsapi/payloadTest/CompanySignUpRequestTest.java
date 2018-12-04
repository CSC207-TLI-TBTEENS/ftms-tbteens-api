package com.ftms.ftmsapi.payloadTest;

import com.ftms.ftmsapi.payload.CompanySignUpRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompanySignUpRequestTest {

    @Test
    public void testId() {
        CompanySignUpRequest request = new CompanySignUpRequest();
        request.setId("new id");
        assertEquals(request.getId(), "new id");
    }

    @Test
    public void testFirstname() {
        CompanySignUpRequest request = new CompanySignUpRequest();
        request.setFirstname("Ece");
        assertEquals(request.getFirstname(), "Ece");
    }

    @Test
    public void testLastname() {
        CompanySignUpRequest request = new CompanySignUpRequest();
        request.setLastname("Yucer");
        assertEquals(request.getLastname(), "Yucer");
    }

    @Test
    public void testNumber() {
        CompanySignUpRequest request = new CompanySignUpRequest();
        request.setNumber("666-666-6666");
        assertEquals(request.getNumber(), "666-666-6666");
    }

    @Test
    public void testEmail() {
        CompanySignUpRequest request = new CompanySignUpRequest();
        request.setEmail("eceyucer@gmail.com");
        assertEquals(request.getEmail(), "eceyucer@gmail.com");
    }

    @Test
    public void testPassword() {
        CompanySignUpRequest request = new CompanySignUpRequest();
        request.setPassword("abcd34");
        assertEquals(request.getPassword(), "abcd34");
    }
}