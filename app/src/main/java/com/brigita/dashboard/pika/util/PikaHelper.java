package com.brigita.dashboard.pika.util;

import android.content.Context;
import android.net.ConnectivityManager;

/*
 * This is a helper class
 */
public class PikaHelper {


    //checks the connectivity status and returns boolean value
    public static boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
