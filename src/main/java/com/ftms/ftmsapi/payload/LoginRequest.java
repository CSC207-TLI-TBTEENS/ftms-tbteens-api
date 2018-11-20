package com.ftms.ftmsapi.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String Email;

    @NotBlank
    private String password;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
