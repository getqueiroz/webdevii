package com.demo.demo.model;

import org.springframework.http.HttpStatusCode;

public class ErrorResponse {
    private HttpStatusCode status;
    private String errorMessage;

    public ErrorResponse(HttpStatusCode status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
    
    public HttpStatusCode getStatus() {
        return status;
    }
    public void setStatus(HttpStatusCode status) {
        this.status = status;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
