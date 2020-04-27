package com.adrian.gameshop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum Platform {
    PS4("PlayStation 4"),
    PS3("PlayStation 3"),
    XBOXONE("Xbox One"),
    XBOX360("Xbox 360"),
    PC("Microsoft Windows");

    private final String name;
}
