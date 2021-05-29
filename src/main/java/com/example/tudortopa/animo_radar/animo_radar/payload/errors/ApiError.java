package com.example.tudortopa.animo_radar.animo_radar.payload.errors;

import lombok.Getter;

import java.util.Date;

@Getter
public class ApiError {
    private Date timestamp = new Date();

    private int status;

    private String error;

    private String message;



    public ApiError(int status, String error) {
        this.status = status;
        this.error = error;
    }

    public ApiError(int status, String error, String message) {
        this(status, error);
        this.message = message;
    }
}
