package com.brigita.dashboard.pika.retrofit;

import java.io.IOException;

/*
 * custom exception class to
 * set the message when connection is lost
 */

public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "Please check your network connection";
    }
}
