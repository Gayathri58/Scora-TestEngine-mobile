package com.brigita.dashboard.pika.test_history;


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

import com.brigita.dashboard.pika.R;
import com.brigita.dashboard.pika.SessionManager;
import com.brigita.dashboard.pika.databinding.FragmentTestHistoryBinding;
import com.brigita.dashboard.pika.retrofit.RetrofitService;
import com.brigita.dashboard.pika.retrofit.RetrofitServiceGenerator;
import com.brigita.dashboard.pika.test_history.adapter.TestHistoryAdapter;
import com.brigita.dashboard.pika.test_history.areachart.model.AreaChartRes;
import com.brigita.dashboard.pika.test_history.model.Attempt;
import com.brigita.dashboard.pika.test_history.model.TestDtl;
import com.brigita.dashboard.pika.test_history.model.TestHistoryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestHistoryFragment extends Fragment {
    private SessionManager sessionManager;
    private static final String TAG = TestHistoryFragment.class.getSimpleName();
    FragmentTestHistoryBinding testHistoryBinding;
    List<Attempt> attemptList;
    List<String> srtAttempts = new ArrayList<>();
    List<String> srtTestName = new ArrayList<>();
    List<String> srtTestDate = new ArrayList<>();
    ArrayList<AreaChartRes> areaChartResList = new ArrayList<>() ;
    List<String> srtPercentScore = new ArrayList<>();

    ImageView noDataIcon ;
    TextView noDataTv;

    ProgressBar progressBar;

    public TestHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

      //  ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        testHistoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_test_history, container, false);

        noDataIcon = testHistoryBinding.noDataIcon.findViewById(R.id.no_data_icon);
        noDataTv = testHistoryBinding.noDataTv.findViewById(R.id.no_data_tv);

        progressBar = testHistoryBinding.progressBarTestHistory.findViewById(R.id.progress_bar_test_history);

        return testHistoryBinding.getRoot();
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sessionManager = new SessionManager(getContext());
        getTestHistory();
    }

    void getTestHistory() {

        // test history api call code here

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getContext());

        Log.e(TAG, "getOrgId " + sessionManager.getOrgId());

        Call<TestHistoryResponse> testresponsecall = retrofitService.getdashBoardTestHistory(sessionManager.getOrgId());

        testresponsecall.enqueue(new Callback<TestHistoryResponse>() {
            @Override
            public void onResponse(Call<TestHistoryResponse> call, Response<TestHistoryResponse> response) {
                if (response.isSuccessful()) {

                    List<TestDtl> testDtlList = response.body().getTestDtl();

                    for (int i = 0; i < testDtlList.size(); i++) {

                        attemptList = testDtlList.get(i).getAttempts();

                        Log.e("attempts list", String.valueOf(attemptList.size()));

                        for (int j = 0; j < attemptList.size(); j++) {

                            srtAttempts.add(String.valueOf(attemptList.get(j).getCurrentAttempt()));
                            srtTestName.add(attemptList.get(j).getTestName());
                            srtTestDate.add(attemptList.get(j).getEndTime());
                            srtPercentScore.add(attemptList.get(j).getPercentScore());

                            Integer strAttempt = attemptList.get(j).getCurrentAttempt();
                            String strName = attemptList.get(j).getTestName();
                            String strDate = attemptList.get(j).getEndTime();
                            String strPeresentScore = attemptList.get(j).getPercentScore();
                            Integer strOrgId = attemptList.get(j).getOrgId();
                            Integer strTestid = attemptList.get(j).getTestId();
                            Integer strTestInstanceId = attemptList.get(j).getTestInstanceId();
                            Integer strScheduleId = attemptList.get(j).getScheduleId();

                            AreaChartRes areaChartRes = new AreaChartRes(strName,strOrgId,strTestid,strAttempt,strScheduleId,strDate,strPeresentScore);
                            areaChartResList.add(areaChartRes);

                            Log.e("afrray list" , String.valueOf(areaChartResList));
                        }
                    }

                    Log.e("attempts ", String.valueOf(srtAttempts.size()));

                    //TestHistoryAdapter adapter = new TestHistoryAdapter(srtAttempts,srtTestName,srtTestDate,srtPercentScore, getContext());

                    if(areaChartResList.size() == 0 ){

                        progressBar.setVisibility(View.GONE);
                        //testHistoryBinding.dashboardTestHistoryTv.setVisibility(View.GONE);
                        noDataIcon.setVisibility(View.VISIBLE);
                        noDataTv.setVisibility(View.VISIBLE);

                    }

                    progressBar.setVisibility(View.GONE);
                    TestHistoryAdapter adapter = new TestHistoryAdapter(areaChartResList, getContext());

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

                    testHistoryBinding.rvDashboardTestHistory.setLayoutManager(linearLayoutManager);
                    testHistoryBinding.rvDashboardTestHistory.setAdapter(adapter);

                }else {

                    progressBar.setVisibility(View.GONE);
                    noDataIcon.setVisibility(View.GONE);
                    noDataTv.setVisibility(View.GONE);

                    Log.e("error code", String.valueOf(response.code()));
                    Log.e("error msg", String.valueOf(response.message()));

                    // test history error handling code here

                    if(response.code() == 404 ){

                        testHistoryBinding.pageNotFoundIconHistory.setVisibility(View.VISIBLE);

                        new AlertDialog.Builder(getContext()).
                                setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                })
                                .setCancelable(false)
                                .setMessage("404, Not Found").create().show();

                    }else  if(response.code() == 401) {

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

                    }else  if(response.code() == 400) {

                        testHistoryBinding.badRequestIconDashboardHistory.setVisibility(View.VISIBLE);

                        new AlertDialog.Builder(getContext()).
                                setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setCancelable(false)
                                .setMessage("400, Bad Request Error").create().show();

                    }else {

                        testHistoryBinding.serverErrorIconDashboardHistory.setVisibility(View.VISIBLE);

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
            public void onFailure(Call<TestHistoryResponse> call, Throwable t) {

                Log.e(TAG, "onFailure " + t.getCause());
                Log.e(TAG, "onFailure " + t.getMessage());
            }
        });

    }
}
