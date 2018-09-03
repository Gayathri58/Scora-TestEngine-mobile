package com.brigita.dashboard.pika.retrofit;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.brigita.dashboard.pika.SessionManager;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/*
 * This class used to check the Authentication status
 * If the error code is 401
 * this will clear the session and
 * redirect the user to sign in activity
 */

public class TokenAuthenticator implements Authenticator {

    private static final String TAG = TokenAuthenticator.class.getSimpleName();

    private Context context;

    public TokenAuthenticator(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public Request authenticate(Route route, Response response) {

        Log.e(TAG, "response " + response.code());

        if(response.code()==401){

            SessionManager sessionManager =new SessionManager(context);
            sessionManager.logoutUser();
            return null;
        }

        return response.request();
    }
}
