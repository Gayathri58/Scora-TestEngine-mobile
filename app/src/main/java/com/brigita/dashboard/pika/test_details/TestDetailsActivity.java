package com.brigita.dashboard.pika.test_details;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.brigita.dashboard.pika.Const.Const;
import com.brigita.dashboard.pika.R;
import com.brigita.dashboard.pika.SessionManager;
import com.brigita.dashboard.pika.databinding.ActivityTestDetailsBinding;
import com.brigita.dashboard.pika.retrofit.RetrofitService;
import com.brigita.dashboard.pika.retrofit.RetrofitServiceGenerator;
import com.brigita.dashboard.pika.take_test.model.TakeTestResponse;
import com.brigita.dashboard.pika.test_instruction.InstructionFragment;
import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class TestDetailsActivity extends AppCompatActivity {

    private static final String TAG = TestDetailsActivity.class.getSimpleName();

    ActivityTestDetailsBinding binding;
    private SessionManager sessionManager;

    ProgressDialog dialog ;
    ImageView userLogo ;

    CurrentSection currentSection = new CurrentSection();
    List<Object> user_selected_option = new ArrayList<>();
    List<Object> user_storage_data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_test_details);

        userLogo = binding.userlogImgView.findViewById(R.id.userlog_img_view);

        sessionManager = new SessionManager(this);

        dialog = ProgressDialog.show(TestDetailsActivity.this, "",
                "Loading your test details...", true);


        getTestDetails();

          binding.proceedTestDetailsTv.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  binding.progressBarTestDetails.setVisibility(View.VISIBLE);

                  Intent intent = new Intent(getApplicationContext(), InstructionFragment.class);
                  startActivity(intent);

                  binding.progressBarTestDetails.setVisibility(View.GONE);
              }
          });

          binding.backTestDetailsTv.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  onBackPressed();

              }
          });
    }


    void getTestDetails() {

            RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(this);

            JsonObject gsonObject = new JsonObject();
            JsonObject paramObject = new JsonObject();
            JsonObject current_section = new JsonObject();

            paramObject.addProperty("test_id", Const.test_id_instruction_str);
            paramObject.addProperty("user_id", 12);
            paramObject.addProperty("org_id", Const.org_id_instruction);
            paramObject.addProperty("schedule_id", Const.schedule_id_instruction);
            paramObject.addProperty("next_section_id", "");
            paramObject.addProperty("device_type", 2);
            paramObject.addProperty("group_id", "null");
            paramObject.addProperty("current_section", String.valueOf(current_section));

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(paramObject.toString());

            retrofit2.Call<TakeTestResponse> userCall = retrofitService.takeTest(gsonObject);
            userCall.enqueue(new Callback<TakeTestResponse>() {
                @Override
                public void onResponse(retrofit2.Call<TakeTestResponse> call, Response<TakeTestResponse> response) {

                    if (response.isSuccessful()) {

                        TakeTestResponse takeTestResponse = response.body();

                        Log.e(TAG, takeTestResponse.orgName);

                        String url = takeTestResponse.userLogo;

                        Glide
                                .with(getApplicationContext())
                                .load(url)
                                .into(userLogo);

                        binding.userNameTestDetails.setText(takeTestResponse.userName);

                        binding.testNameTestDetailsTv.setText(takeTestResponse.testName);

                        binding.orgNameTestDetailsTv.setText(takeTestResponse.orgName);

                        Integer hours = takeTestResponse.timer / 3600;
                        Integer minutes = (takeTestResponse.timer % 3600) / 60;
                        Integer seconds = takeTestResponse.timer % 60 ;

                        binding.durationTestDetailsTv.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

                        dialog.dismiss();

                        Log.e(TAG, response.message());

                    } else {

                        Log.e("fail", response.message());


                    }
                }

                @Override
                public void onFailure(retrofit2.Call<TakeTestResponse> call, Throwable t) {

                }
            });
        }

}

