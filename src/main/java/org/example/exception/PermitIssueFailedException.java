package org.example.exception;

public class PermitIssueFailedException extends Exception {

    public PermitIssueFailedException() {
        super("Permit Issue Failed Try Again");
    }

    public PermitIssueFailedException(String message) {
        super(message);
    }
}
