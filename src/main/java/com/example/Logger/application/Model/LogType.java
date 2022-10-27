package com.example.Logger.application.Model;

public enum LogType {
    ERROR(0),
    WARNING(1),
    INFO(2);

    public final int value;
    private LogType(int value) {
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
