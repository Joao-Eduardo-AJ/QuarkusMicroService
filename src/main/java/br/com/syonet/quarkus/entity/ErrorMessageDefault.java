package br.com.syonet.quarkus.entity;

import jakarta.persistence.Column;

public class ErrorMessageDefault {

    @Column(name = "error")
    private boolean error;

    @Column(name = "message")
    private String message;

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean getError() {
        return error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
