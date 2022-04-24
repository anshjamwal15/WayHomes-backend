package com.org.dumper.payload.request;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class UserProfileRequest {

//    private String firstName;
//
//    private String lastName;

    private String email;

    private String username;

    private String password;
}
