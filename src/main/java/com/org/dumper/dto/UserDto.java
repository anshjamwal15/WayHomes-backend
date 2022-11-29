package com.org.dumper.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private Set<RoleDto> roles;

}
