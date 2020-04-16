package com.app.coronatracker.ui.api.utils;

public class APIError implements CTError {

    private String message;
    private int code;

    @Override
    public int getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
