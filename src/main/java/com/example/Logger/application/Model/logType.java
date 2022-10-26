package com.example.Logger.application.Model;

public enum logType {
    ERROR(1),
    WARNING(2),
    INFO(3);

    public final int value;
    private logType(int value) {
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
