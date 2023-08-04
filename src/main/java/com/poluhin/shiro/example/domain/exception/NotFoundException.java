package com.poluhin.shiro.example.domain.exception;

public class NotFoundException extends Exception {

    public NotFoundException(String id) {
        super(id);
    }
}
