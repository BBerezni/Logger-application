package com.example.Logger.application.Model;

public enum logType {
    ERROR(0),
    WARNING(1),
    INFO(2);

    public final int value;
    private logType(int value) {
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
