package com.brigita.dashboard.pika.authentication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.brigita.dashboard.pika.R;
import com.brigita.dashboard.pika.SessionManager;
import com.brigita.dashboard.pika.authentication.model.SignInRequest;
import com.brigita.dashboard.pika.authentication.model.SignInResponse;
import com.brigita.dashboard.pika.databinding.ActivityLoginBinding;
import com.brigita.dashboard.pika.home.HomeActivity;
import com.brigita.dashboard.pika.retrofit.RetrofitService;
import com.brigita.dashboard.pika.retrofit.RetrofitServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    ActivityLoginBinding binding;
    Button login;
    ProgressBar loginProgress;
    TextView loginEmailEdt;
    TextView loginPasswordEdt;

    RetrofitService retrofitService;

    private static final int CLIENT_ID = 2;
    private static final String CLIENT_SECRET = "F06R8OHlPB6Ag92LR9QppHkqxvjytVjhK7p7OxRf";
    private static final String GRANT_TYPE = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        login = binding.loginButton.findViewById(R.id.login_button);
        loginProgress = binding.loginProgress.findViewById(R.id.login_progress);
        loginEmailEdt = binding.loginEmailEditText.findViewById(R.id.login_email_edit_text);
        loginPasswordEdt = binding.loginPasswordEditText.findViewById(R.id.login_password_edit_text);

        checkInternetAccess();

        retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(this);

    }

    private void checkInternetAccess() {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo != null) {
            if (netInfo.isConnected()) {
                Log.e(TAG, "connected");
            }else {
                new AlertDialog.Builder(LoginActivity.this).setMessage("Check the Internet Connection").setPositiveButton("OK", null).show();

            }
        }
    }
    //on click for login button
    public void onClickLogin(View view) {

        String email = binding.loginEmailEditText.getText().toString().trim();
        String password = binding.loginPasswordEditText.getText().toString().trim();

        if (email.isEmpty()) {

            loginEmailEdt.setError("Please enter the Email Id");
            loginEmailEdt.requestFocus();
            return;
        }

        if (password.isEmpty()) {

            loginPasswordEdt.setError("Please enter the Password");
            loginPasswordEdt.requestFocus();
            return;
        }
        view.setEnabled(false);
        checkInternetAccess();
        login();

        loginProgress.setVisibility(View.VISIBLE);

    }

    //on click for forgot password text view
    public void onClickForgotPassword(View view) {

        forgotPassword();
        loginProgress.setVisibility(View.VISIBLE);

    }

    void forgotPassword(){
        Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
        startActivity(intent);
        finish();
    }

    void login() {

        String email = binding.loginEmailEditText.getText().toString().trim();
        String password = binding.loginPasswordEditText.getText().toString().trim();

        SignInRequest signInRequest = new SignInRequest(
                email,
                password,
                CLIENT_ID,
                CLIENT_SECRET,
                GRANT_TYPE
        );

        Call<SignInResponse> signInCall = retrofitService.signIn(signInRequest);

        signInCall.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {

                Log.e(TAG, "code" + response.code());

                if (response.isSuccessful()) {

                    if (response.body().success) {
                        Log.e(TAG, "message " + "success");
                        SessionManager sessionManager = new SessionManager(LoginActivity.this);
                        sessionManager.createLoginSession(response.body().accessToken);

                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.e(TAG, "message " + response.body().message);

                        loginProgress.setVisibility(View.GONE);

                        Snackbar snackbar = Snackbar
                                .make(binding.activityLoginLayout, response.body().message, Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }

                } else {

                    loginProgress.setVisibility(View.GONE);

                    new AlertDialog.Builder(getApplicationContext()).
                            setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setCancelable(false)
                            .setMessage("500, Server takes too long to respond").create().show();
                }

            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Log.e(TAG, "getCause " + t.getCause());
            }
        });


    }

}



