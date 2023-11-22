package com.assignment.autodesk.model;

import lombok.Data;

@Data
public class ReplyMessage {
    private final String message;


    public ReplyMessage(String message) {
        this.message = message;
    }

}
