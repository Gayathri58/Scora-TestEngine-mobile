package com.brigita.dashboard.pika.authentication;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.brigita.dashboard.pika.R;
import com.brigita.dashboard.pika.authentication.model.ForgotPasswordRequest;
import com.brigita.dashboard.pika.authentication.model.ForgotPasswordResponse;
import com.brigita.dashboard.pika.databinding.ActivityForgotPasswordBinding;
import com.brigita.dashboard.pika.retrofit.RetrofitService;
import com.brigita.dashboard.pika.retrofit.RetrofitServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    private static final String TAG = ForgotPasswordActivity.class.getSimpleName();
    ActivityForgotPasswordBinding binding;
    Button forgotPwdOkBtn, forgotPwdCancelBtn;
    ProgressBar forgotPwdOkProBar;
    TextView forgotPasswordEmailEdt;

    RetrofitService retrofitService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);

        forgotPwdOkProBar = binding.forgotPasswordProgress.findViewById(R.id.forgot_password_progress);
        forgotPwdOkBtn = binding.okButton.findViewById(R.id.ok_button);
        forgotPwdCancelBtn = binding.cancelButton.findViewById(R.id.cancel_button);
        forgotPasswordEmailEdt = binding.forgotPasswordEmailEditText.findViewById(R.id.forgot_password_email_edit_text);

        retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(this);
    }

    public void onClickForgotpwdOk(View view) {

        String email = binding.forgotPasswordEmailEditText.getText().toString().trim();

        if (email.isEmpty()) {

            forgotPasswordEmailEdt.setError("Email-id required");
            forgotPasswordEmailEdt.requestFocus();
            return;
        }

        forgotpwdOk();
        forgotPwdOkProBar.setVisibility(View.VISIBLE);

    }

    public void onClickForgotPwdCancel(View view) {

        forgotPwdCancel();
    }

    void forgotPwdCancel() {

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();

    }

    void forgotpwdOk() {

        final String email = binding.forgotPasswordEmailEditText.getText().toString().trim();

        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest(email);

        Call<ForgotPasswordResponse> forgotPasswordResponseCall = retrofitService.forgetPassword(forgotPasswordRequest);

        forgotPasswordResponseCall.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {

                Log.e(TAG, "code" + response.code());

                if (response.isSuccessful()) {

                    ForgotPasswordResponse forgotPasswordResponse = response.body();

                    new AlertDialog.Builder(ForgotPasswordActivity.this)
                            .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to invoke NO event
                            dialog.dismiss();

                            forgotPwdOkProBar.setVisibility(View.GONE);

                            forgotPwdCancel();
                        }
                    })
                            .setTitle("Forgot Password")

                            .setMessage(forgotPasswordResponse.getMessage())

                            .show();

                } else {

                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
            }
        });

    }
}
