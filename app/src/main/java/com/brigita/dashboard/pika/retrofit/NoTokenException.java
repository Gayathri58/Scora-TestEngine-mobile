package com.brigita.dashboard.pika.retrofit;

import java.io.IOException;

/*
 * Custom exception class to
 * set message when access token is null
 */

public class NoTokenException extends IOException {

    @Override
    public String getMessage() {
        return "Please Login, Session has Expired!";
    }
}