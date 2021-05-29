package com.example.tudortopa.animo_radar.animo_radar.payload.errors;

public class BadRequestError extends ApiError {
    public BadRequestError() {
        super(400, "Bad Request");
    }

    public BadRequestError(String message) {
        super(400, "Bad Request", message);
    }
}