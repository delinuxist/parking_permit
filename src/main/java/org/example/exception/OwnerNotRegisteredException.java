package org.example.exception;

public class OwnerNotRegisteredException extends Exception{

    public OwnerNotRegisteredException(){
        this("Owner Not Registered");
    }
    public OwnerNotRegisteredException(String message) {
        super(message);
    }
}
