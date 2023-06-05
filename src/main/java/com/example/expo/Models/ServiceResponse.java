package com.example.expo.Models;

public class ServiceResponse {
   public Boolean success;
    public String message;

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
