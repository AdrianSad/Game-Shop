package com.adrian.gameshop.models;

import lombok.Setter;

public enum Platform {
    PS4("PlayStation4"),
    XBOXONE("Xbox One"),
    PC("Microsoft Windows");

    private String name;

    Platform(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
