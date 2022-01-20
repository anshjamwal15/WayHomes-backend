package com.org.dumper.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {

    public String token;

    public String type = "Bearer";

    public Long id;

    public String username;

    public String email;

    public List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
