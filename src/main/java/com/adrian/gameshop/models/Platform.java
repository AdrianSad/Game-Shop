package com.adrian.gameshop.models;

import lombok.Setter;

public enum Platform {
    PS4("PlayStation 4"),
    PS3("PlayStation 3"),
    XBOXONE("Xbox One"),
    XBOX360("Xbox 360"),
    PC("Microsoft Windows");

    private String name;

    Platform(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
