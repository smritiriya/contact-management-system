package com.contactmanagement.entity;

import lombok.Data;

@Data
public class ExceptionResponse {
    private String timestamp;
    private String error;
    private String status;
    private String path;
}
