package com.contactmanagement.customexceptions;

public class ContactNotFound extends RuntimeException {

    public ContactNotFound(String message) {
        super(message);
    }

}
