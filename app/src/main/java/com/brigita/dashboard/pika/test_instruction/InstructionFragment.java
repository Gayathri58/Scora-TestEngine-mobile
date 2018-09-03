package com.brigita.dashboard.pika.test_instruction;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.brigita.dashboard.pika.Const.Const;
import com.brigita.dashboard.pika.R;
import com.brigita.dashboard.pika.databinding.FragmentInstructionBinding;
import com.brigita.dashboard.pika.retrofit.RetrofitService;
import com.brigita.dashboard.pika.retrofit.RetrofitServiceGenerator;
import com.brigita.dashboard.pika.take_test.TakeTestFragment;
import com.brigita.dashboard.pika.take_test.model.TakeTestResponse;
import com.brigita.dashboard.pika.test_details.CurrentSection;
import com.brigita.dashboard.pika.test_instruction.model.Instruction;
import com.brigita.dashboard.pika.test_instruction.model.InstructionRequest;
import com.brigita.dashboard.pika.test_instruction.model.InstructionResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstructionFragment extends AppCompatActivity {

    private static final String TAG = InstructionFragment.class.getSimpleName();

    private CurrentSection currentSection = new CurrentSection();
    private List<Object> user_selected_option = new ArrayList<>();
    private List<Object> user_storage_data = new ArrayList<>();

    FragmentInstructionBinding binding;

    ImageView orgIconTakeTest;

    TextView startTest;


    public InstructionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.fragment_instruction);

        startTest = binding.readyToBegin.findViewById(R.id.ready_to_begin);

        orgIconTakeTest = findViewById(R.id.org_icon_take_test);

        getInstruction();
        //getTestTest();
        binding.acceptChkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (((CheckBox) view).isChecked()) {

                    binding.readyToBegin.setVisibility(View.VISIBLE);

                } else {

                    binding.readyToBegin.setVisibility(View.GONE);

                }
            }
        });

        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.instructionProgress.setVisibility(View.VISIBLE);

                Fragment fragment;
                fragment = new TakeTestFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_take_take_fragment, fragment).commit();

                binding.instructionProgress.setVisibility(View.GONE);
                startTest.setVisibility(View.GONE);
                binding.acceptChkBox.setVisibility(View.GONE);
                binding.rulesInstructionTv.setVisibility(View.GONE);

            }
        });
    }


    public void fetchdata() {

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getApplicationContext());

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


    void getInstruction() {

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getApplicationContext());

        InstructionRequest instructionRequest = new InstructionRequest(Const.test_id_instruction, Const.org_id_instruction, Const.user_id_instruction);

        Call<InstructionResponse> instructionResponseCall = retrofitService.getInstrutionText(instructionRequest);

        instructionResponseCall.enqueue(new Callback<InstructionResponse>() {
            @Override
            public void onResponse(Call<InstructionResponse> call, Response<InstructionResponse> response) {

                if (response.isSuccessful()) {

                    List<Instruction> instructions = new ArrayList<>();

                    instructions = response.body().getInstructions();

                    for (int i = 0; i < instructions.size(); i++) {


                        String rules = instructions.get(i).getTestRule();
                        binding.rulesInstructionTv.setText(Html.fromHtml(rules));
                        Const.InstructionStr = rules;

                    }

                }

            }

            @Override
            public void onFailure(Call<InstructionResponse> call, Throwable t) {

            }
        });
    }

}
