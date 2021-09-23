package com.example.actividad6.model;

public class Message {

    private String change;
    private int movementValue;

    public Message(String change, int movementValue) {
        this.change = change;
        this.movementValue = movementValue;
    }

    public Message() {}

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public int getMovementValue() {
        return movementValue;
    }

    public void setMovementValue(int movementValue) {
        this.movementValue = movementValue;
    }
}
