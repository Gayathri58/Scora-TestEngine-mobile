package com.brigita.dashboard.pika.retrofit;

import android.content.Context;
import android.util.Log;

import com.brigita.dashboard.pika.SessionManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*
 * This class used to add access token to the header
 * if token is null the user will be logged out
 */

public class TokenInterceptor implements  Interceptor{

    private static final String TAG = TokenInterceptor.class.getSimpleName();

    private SessionManager sessionManager;


    TokenInterceptor(Context context) {
        sessionManager = new SessionManager(context);

    }


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        Request.Builder builder =original.newBuilder();

        String accessToken;
        if (sessionManager.isLoggedIn()){

            accessToken= sessionManager.getAccessToken();

            if (null != accessToken) {
                Log.e(TAG, " " + accessToken);

                builder.header("Authorization", "Bearer " + accessToken);

            } else {
                sessionManager.logoutUser();
            }

        }

        //.header("Accept", "application/json");

        Request request = builder.build();
        return chain.proceed(request);
    }


}
