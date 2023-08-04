package com.example.greenhouse.model;

public class NotificationModel {
    private int id;
    private String greenhouseName;
    private String exceededParameter;
    private String message;

    public NotificationModel(int id, String greenhouseName, String exceededParameter, String message) {
        this.id = id;
        this.greenhouseName = greenhouseName;
        this.exceededParameter = exceededParameter;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGreenhouseName() {
        return greenhouseName;
    }

    public void setGreenhouseName(String greenhouseName) {
        this.greenhouseName = greenhouseName;
    }

    public String getExceededParameter() {
        return exceededParameter;
    }

    public void setExceededParameter(String exceededParameter) {
        this.exceededParameter = exceededParameter;
    }



}

