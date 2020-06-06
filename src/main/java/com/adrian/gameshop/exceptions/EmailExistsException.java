package com.adrian.gameshop.exceptions;

public class EmailExistsException extends Throwable {

    public EmailExistsException(final String message){
        super(message);
    }
}
