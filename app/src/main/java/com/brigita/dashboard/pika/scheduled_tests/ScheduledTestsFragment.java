package com.brigita.dashboard.pika.scheduled_tests;


import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.brigita.dashboard.pika.Const.Const;
import com.brigita.dashboard.pika.R;
import com.brigita.dashboard.pika.SessionManager;
import com.brigita.dashboard.pika.databinding.FragmentScheduledTestsBinding;
import com.brigita.dashboard.pika.retrofit.RetrofitService;
import com.brigita.dashboard.pika.retrofit.RetrofitServiceGenerator;
import com.brigita.dashboard.pika.scheduled_tests.adapter.ScheduledTestAdapter;
import com.brigita.dashboard.pika.scheduled_tests.model.ScheduledTest;
import com.brigita.dashboard.pika.scheduled_tests.model.ScheduledTestResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduledTestsFragment extends Fragment {

    private SessionManager sessionManager;
    private static final String TAG = ScheduledTestsFragment.class.getSimpleName();
    FragmentScheduledTestsBinding binding;
    private List<ScheduledTest> scheduledTestList;

    ProgressBar progressBar;

    ImageView noDataIcon;
    TextView noDataTv;

    public ScheduledTestsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       // ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scheduled_tests, container, false);

        noDataIcon = binding.noDataIcon.findViewById(R.id.no_data_icon);
        noDataTv = binding.noDataTv.findViewById(R.id.no_data_tv);

        progressBar = binding.progressBarScheduleTest.findViewById(R.id.progress_bar_schedule_test);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sessionManager = new SessionManager(getContext());
        getScheduledTest();
    }

    void getScheduledTest() {

        // schedule test api call code here

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getContext());

        Log.e(TAG, "getOrgId " + sessionManager.getOrgId());

        final Call<ScheduledTestResponse> callScheduledTests = retrofitService.getScheduledTests(sessionManager.getOrgId());

        callScheduledTests.enqueue(new Callback<ScheduledTestResponse>() {
            @Override
            public void onResponse(Call<ScheduledTestResponse> call, Response<ScheduledTestResponse> response) {

                if (response.isSuccessful()) {

                    scheduledTestList = response.body().getScheduledTests();

                    Const.time_zone = response.body().getTimezone();

                    List expired = new ArrayList();

                    for (int k = 0 ; k < scheduledTestList.size() ; k++){

                        if (scheduledTestList.get(k).testStatus == 3 ){
                            expired.add(scheduledTestList.get(k));
                        }

                    }

                    if (scheduledTestList == null || scheduledTestList.size() == expired.size() ) {

                        //binding.dashboardScheduledTestsLabel.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);
                        noDataIcon.setVisibility(View.VISIBLE);
                        noDataTv.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                    }else {

                        List list = new ArrayList<>();

                        for (int i = 0; i < scheduledTestList.size(); i++) {

                            if ((scheduledTestList.get(i).testStatus == 1 || scheduledTestList.get(i).testStatus == 2) &&
                                    (scheduledTestList.get(i).attemptCount <= scheduledTestList.get(i).totalCount )
                                    ) {

                                list.add(scheduledTestList.get(i));


                            }

                        }

                        scheduledTestList.clear();

                        scheduledTestList = list;

                        progressBar.setVisibility(View.GONE);

                        ScheduledTestAdapter adapter = new ScheduledTestAdapter(scheduledTestList, getContext());

                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

                        binding.dashboardScheduledTestsRv.setLayoutManager(layoutManager);
                        binding.dashboardScheduledTestsRv.setNestedScrollingEnabled(false);
                        binding.dashboardScheduledTestsRv.setAdapter(adapter);
                    }

                } else {

                    progressBar.setVisibility(View.GONE);

                    noDataIcon.setVisibility(View.GONE);
                    noDataTv.setVisibility(View.GONE);

                    Log.e("error code", String.valueOf(response.code()));
                    Log.e("error msg", String.valueOf(response.message()));

                    if (response.code() == 404) {

                        binding.pageNotFoundIconSchedule.setVisibility(View.VISIBLE);

                        // test history error handling code here

                        new AlertDialog.Builder(getContext()).
                                setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                })
                                .setCancelable(false)
                                .setMessage("404, Not Found").create().show();

                    } else if (response.code() == 401) {
                        new AlertDialog.Builder(getContext()).
                                setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        sessionManager.logoutUser();
                                    }
                                })
                                .setCancelable(false)
                                .setMessage("401, Unauthorized error").create().show();

                    } else if (response.code() == 400) {

                        binding.badRequestIconDashboardSchedule.setVisibility(View.VISIBLE);

                        new AlertDialog.Builder(getContext()).
                                setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setCancelable(false)
                                .setMessage("400, Bad Request Error").create().show();

                    } else {

                        binding.serverErrorIconSchedule.setVisibility(View.VISIBLE);
                        new AlertDialog.Builder(getContext()).
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

            }

            @Override
            public void onFailure(Call<ScheduledTestResponse> call, Throwable t) {

                Log.e(TAG, "onFailure " + t.getCause());
                Log.e(TAG, "onFailure " + t.getMessage());

                Toast.makeText(getContext(), "No Group Scheduled", Toast.LENGTH_LONG).show();

            }
        });
    }
}
