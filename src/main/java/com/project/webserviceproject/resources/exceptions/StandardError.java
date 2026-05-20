package com.project.webserviceproject.resources.exceptions;

import java.time.Instant;

public class StandardError extends RuntimeException {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError( Instant timestamp, Integer status, String error,String message, String path) {
        this.error = error;
        this.message = message;
        this.path = path;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
