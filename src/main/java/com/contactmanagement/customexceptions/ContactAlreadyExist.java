package com.contactmanagement.customexceptions;

public class ContactAlreadyExist extends RuntimeException {
    public ContactAlreadyExist(String message) {
        super(message);
    }
}
