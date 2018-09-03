package com.brigita.dashboard.pika;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.brigita.dashboard.pika.authentication.LoginActivity;
import com.brigita.dashboard.pika.databinding.ActivitySplashScreenBinding;
import com.brigita.dashboard.pika.home.HomeActivity;

/**
 * splash screen or welcome screen activity
 */

public class SplashScreenActivity extends AppCompatActivity {

    ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);

        checkInternetAccess();


    }

    private void checkInternetAccess() {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null) {
            new AlertDialog.Builder(SplashScreenActivity.this).setMessage("Check the Internet Connection").show();
            finish();

        }else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    //Check session status and redirect user
                    //to the appropriate screen

                    SessionManager sessionManager = new SessionManager(SplashScreenActivity.this);
                    Intent intent;

                    if (sessionManager.isLoggedIn()) {

                        intent = new Intent(getApplicationContext(), HomeActivity.class);

                    } else {

                        intent = new Intent(getApplicationContext(), LoginActivity.class);
                    }
                    startActivity(intent);
                    finish();

                }
            }, 2000);  // delay in Milliseconds

        }
    }


}
