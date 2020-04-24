package com.adrian.gameshop.models;


import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    @Id
    private String id;
    private String name;
}
