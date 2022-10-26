package com.example.Logger.application.Model;

public enum UserAuth {
    CLIENT(1),
    ADMIN(2);

    public final int value;

    private UserAuth(int value) {
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
