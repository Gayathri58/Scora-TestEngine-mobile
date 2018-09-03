package com.brigita.dashboard.pika.retrofit;

import android.content.Context;

import com.brigita.dashboard.pika.util.PikaHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*
 * This class throws a custom exception
 * when Internet connectivity is not available
 * The status can be captured in retrofits on failure method
 */
public class ConnectivityInterceptor implements Interceptor {

    private Context mContext;

    public ConnectivityInterceptor(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        if(!PikaHelper.isOnline(mContext)){
            throw new NoConnectivityException();
        }

        Request.Builder builder =chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}