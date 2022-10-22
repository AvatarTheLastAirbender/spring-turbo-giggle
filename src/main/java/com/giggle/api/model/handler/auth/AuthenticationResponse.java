package com.giggle.api.model.handler.auth;

public class AuthenticationResponse {
    final private String Token;

    public AuthenticationResponse(String token) {
        this.Token = token;
    }

    public String getToken() {
        return Token;
    }
}
