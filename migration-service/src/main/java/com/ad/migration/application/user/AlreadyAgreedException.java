package com.ad.migration.application.user;

public class AlreadyAgreedException extends RuntimeException {
    public AlreadyAgreedException(String formatted) {
        super(formatted);
    }
}
