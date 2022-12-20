package com.org.dumper.payload.response;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private String role;

    public UserResponse(Long id, String username, String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}