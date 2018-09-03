package com.brigita.dashboard.pika.retrofit;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * This class is used to build a static
 * retrofit client used by the entire app
 */

public class RetrofitServiceGenerator {

    private static final String ENDPOINT = "http://pikaaccounts.brigita.co/pikaaccounts/public/";
    // private static final String ENDPOINT = "http://192.168.1.20/Pika-Account/public/";

    private static RetrofitService retrofitOAuthClient = null;
    private static RetrofitService retrofitClient = null;


    public static RetrofitService getRetrofitClient(Context mContext) {

        if (retrofitClient == null) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(new ConnectivityInterceptor(mContext))
                    .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            retrofitClient = retrofit.create(RetrofitService.class);
        }
        return retrofitClient;
    }


    public static RetrofitService getRetrofitOauthClient(Context mContext) {

        if (retrofitOAuthClient == null) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .authenticator(new TokenAuthenticator(mContext))
                    .addInterceptor(new TokenInterceptor(mContext))
                    .addInterceptor(new ConnectivityInterceptor(mContext))
                    .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            retrofitOAuthClient = retrofit.create(RetrofitService.class);
        }
        return retrofitOAuthClient;
    }

}
