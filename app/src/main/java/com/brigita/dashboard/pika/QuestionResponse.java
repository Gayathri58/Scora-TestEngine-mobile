package com.brigita.dashboard.pika;

import android.content.Context;
import android.util.Log;

import com.brigita.dashboard.pika.retrofit.RetrofitService;
import com.brigita.dashboard.pika.retrofit.RetrofitServiceGenerator;
import com.brigita.dashboard.pika.take_test.model.TakeTestResponse;
import com.brigita.dashboard.pika.test_details.CurrentSection;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class QuestionResponse {

    private static final String TAG = QuestionResponse.class.getSimpleName();

    DataInterface mListener;
    private CurrentSection currentSection = new CurrentSection();
    private List<Object> user_selected_option = new ArrayList<>();
    private List<Object> user_storage_data = new ArrayList<>();

    Context context ;

    public QuestionResponse() {
        super();
    }


    public void fetchdata() {

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(context);

        JsonObject gsonObject = new JsonObject();
        JsonObject paramObject = new JsonObject();
        JsonObject current_section = new JsonObject();

        paramObject.addProperty("test_id", 46);
        paramObject.addProperty("user_id", 12);
        paramObject.addProperty("org_id", 6);
        paramObject.addProperty("schedule_id", 189);
        paramObject.addProperty("next_section_id","");
        paramObject.addProperty("device_type",2);
        paramObject.addProperty("group_id", "null");
        paramObject.addProperty("current_section", String.valueOf(current_section));

        JsonParser jsonParser = new JsonParser();
        gsonObject = (JsonObject) jsonParser.parse(paramObject.toString());

        retrofit2.Call<TakeTestResponse> userCall = retrofitService.takeTest(gsonObject) ;
        userCall.enqueue(new Callback<TakeTestResponse>() {
            @Override
            public void onResponse(retrofit2.Call<TakeTestResponse> call, Response<TakeTestResponse> response) {

                if (response.isSuccessful()) {

                    if (response != null && response.body() != null && mListener != null) {
                        mListener.responseData(response.body());

                        Log.e("success", response.message());

                    }
                }else {

                    Log.e("fail", response.message());


                }
            }

            @Override
            public void onFailure(retrofit2.Call<TakeTestResponse> call, Throwable t) {

            }
        });
    }


    public void setOnDataListener(DataInterface listener) {
        mListener = listener;
    }

    public interface DataInterface {
        void responseData(TakeTestResponse myResponse);

    }

}

   /* public void getData() {

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitClient(context);

        TakeTestRequest takeTestRequest = new TakeTestRequest(currentSection, 2, "null",
                "null", "3", "null",
                "125", "45", "3", user_selected_option, user_storage_data);

        Call<TakeTestResponse> takeTestResponseCall = retrofitService.testDetails(takeTestRequest);

        takeTestResponseCall.enqueue(new Callback<TakeTestResponse>() {
            @Override
            public void onResponse(Call<TakeTestResponse> call, Response<TakeTestResponse> response) {

                if (response.isSuccessful()) {

                    if (response != null && response.body() != null && mListener != null) {
                        mListener.responseData(response.body());
                        Log.e(TAG, String.valueOf(response.code()));
                    }

                } else {

                    Log.e(TAG, String.valueOf(response.code()));

                    try {
                        Log.e(TAG, String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(Call<TakeTestResponse> call, Throwable t) {

            }
        });


    }*/


