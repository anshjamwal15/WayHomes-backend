package com.org.dumper.payload.response;

import lombok.Data;

@Data
public class MessageResponse {

    public String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
