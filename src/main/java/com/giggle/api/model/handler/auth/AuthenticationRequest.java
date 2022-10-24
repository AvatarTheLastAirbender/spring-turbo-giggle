package com.giggle.api.model.handler.auth;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthenticationRequest {
    @NotNull
    private String username;
    @NotNull
    @Size(min = 8, max = 20, message = "Minimum password length must be 6")
    private String password;


    public AuthenticationRequest() {
        super();
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("UserName : %s, Password : %s", this.username, this.password);
    }
}
