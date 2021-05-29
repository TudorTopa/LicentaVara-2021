package com.example.tudortopa.animo_radar.animo_radar.payload.responses;

import java.util.List;

public class JwtResponse {
    private String username;
    private List<String> roles;
    private String token;
    private String type = "Bearer";

    public JwtResponse(String accessToken, String username, List<String> roles) {
        this.username = username;
        this.roles = roles;
        this.token = accessToken;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return type;
    }
}