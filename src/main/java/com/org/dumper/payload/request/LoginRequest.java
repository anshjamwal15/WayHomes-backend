package com.org.dumper.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank
    public String username;

    @NotBlank
    public String password;

    public String type;
}
