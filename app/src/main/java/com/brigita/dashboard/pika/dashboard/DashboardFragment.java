package com.brigita.dashboard.pika.dashboard;


import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.brigita.dashboard.pika.Const.Const;
import com.brigita.dashboard.pika.R;
import com.brigita.dashboard.pika.SessionManager;
import com.brigita.dashboard.pika.dashboard.adapters.TestDetailsDrillAdaper;
import com.brigita.dashboard.pika.dashboard.model.PerformanceAnalysi;
import com.brigita.dashboard.pika.dashboard.model.PieChartResponse;
import com.brigita.dashboard.pika.dashboard.model.RecentActivityLog;
import com.brigita.dashboard.pika.dashboard.model.TestDetailsDrilResponse;
import com.brigita.dashboard.pika.dashboard.model.TestGroup;
import com.brigita.dashboard.pika.dashboard.model.TestGroupDrillResponse;
import com.brigita.dashboard.pika.dashboard.model.TestPerformanceDrillResponse;
import com.brigita.dashboard.pika.dashboard.model.TestRecentActivityDrillResponse;
import com.brigita.dashboard.pika.dashboard.model.TestSummary;
import com.brigita.dashboard.pika.databinding.FragmentDashboardBinding;
import com.brigita.dashboard.pika.retrofit.RetrofitService;
import com.brigita.dashboard.pika.retrofit.RetrofitServiceGenerator;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {


    private static final String TAG = DashboardFragment.class.getSimpleName();

    FragmentDashboardBinding binding;
    private SessionManager sessionManager;

    TextView performanceSummaryTv;
    TextView testSummaryTv;
    TextView testGroupTv;
    TextView performanceAnalysiTv;
    TextView recentActivityLogTv;

    PieChart pieChart;
    PieChart testGroupPieChart;
    LineChart recAcivity;
    BarChart barChart;

    BarChart testGroupTestPerformance;
    List<TestGroupDrillResponse> testGroupDrillResponseList;
    LineChart testPerformanceAttempts;
    List<TestPerformanceDrillResponse> testPerformanceDrillResponseList;
    BarChart recentActivityPerformance;
    List<TestRecentActivityDrillResponse> testRecentActivityDrillResponseList;

    List<TestSummary> testSummaryList;
    List<TestGroup> testGroupList;
    List<PerformanceAnalysi> performanceAnalysiList;
    List<RecentActivityLog> recentActivityLogList;

    ProgressBar progressBar;
    ImageView noDataIcon;
    TextView noDataTv;

    ImageView backButton;
    ImageView testGroupBackBtn;
    ImageView testPerformanceBackBtn;
    ImageView recentActivityPerformanceBackBtn;


    public DashboardFragment() {

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);

        progressBar = binding.progressBarDashBoard.findViewById(R.id.progress_bar_dash_board);
        noDataIcon = binding.noChartDataIcon.findViewById(R.id.no_chart_data_icon);
        noDataTv = binding.noChartDataTv.findViewById(R.id.no_chart_data_tv);

        backButton = binding.backBtn.findViewById(R.id.back_btn);
        testGroupBackBtn = binding.testGroupBackBtn.findViewById(R.id.test_group_back_btn);
        testPerformanceBackBtn = binding.testPerformanceBackBtn.findViewById(R.id.test_performance_back_btn);
        recentActivityPerformanceBackBtn = binding.recentActivityBackBtn.findViewById(R.id.recent_activity_back_btn);

        performanceSummaryTv = binding.preformSummary.findViewById(R.id.preform_summary);

        testGroupTestPerformance = binding.testGroupPreformanceBarchart.findViewById(R.id.test_group_preformance_barchart);
        testPerformanceAttempts = binding.testPerformanceAttemptsLineChart.findViewById(R.id.test_performance_attempts_line_chart);
        recentActivityPerformance = binding.recectActivityPerformanceBar.findViewById(R.id.recect_activity_performance_bar);

        pieChart = binding.testSummaryPieChart.findViewById(R.id.test_summary_pie_chart);
        testSummaryTv = binding.testSummaryTv.findViewById(R.id.test_summary_tv);

        testGroupPieChart = binding.testGroupPieChart.findViewById(R.id.test_group_pie_chart);
        testGroupTv = binding.testGroupTv.findViewById(R.id.test_group_tv);

        barChart = binding.preformanceBarchart.findViewById(R.id.preformance_barchart);
        performanceAnalysiTv = binding.preformanceAnalysisTv.findViewById(R.id.preformance_analysis_tv);

        recAcivity = binding.recActivityLineChart.findViewById(R.id.rec_activity_line_chart);
        recentActivityLogTv = binding.recentActivityTv.findViewById(R.id.recent_activity_tv);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sessionManager = new SessionManager(getContext());

        getDashBoard();
        testSummaryDesc();

        binding.drillDownTestSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext()).
                        setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setCancelable(false).setMessage("Click on data labels to drill down ").create().show();
            }
        });

        binding.drillDownPerformanceAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext()).
                        setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setCancelable(false).setMessage("Click on data labels to drill down ").create().show();
            }
        });

        binding.drillDownTestGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext()).
                        setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setCancelable(false).setMessage("Click on data labels to drill down ").create().show();
            }
        });

        binding.drillDownActivityLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext()).
                        setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setCancelable(false).setMessage("Click on data labels to drill down ").create().show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drillDownTestSummary.setVisibility(View.VISIBLE);
                pieChart.setVisibility(View.VISIBLE);
                binding.testSummaryClickRecView.setVisibility(View.GONE);
                backButton.setVisibility(View.GONE);

            }
        });

        testGroupBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drillDownTestGroup.setVisibility(View.VISIBLE);
                testGroupPieChart.setVisibility(View.VISIBLE);
                testGroupTestPerformance.setVisibility(View.GONE);
                binding.testGroupNoDataIcon.setVisibility(View.GONE);
                testGroupBackBtn.setVisibility(View.GONE);
            }
        });

        testPerformanceBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drillDownPerformanceAnalysis.setVisibility(View.VISIBLE);
                barChart.setVisibility(View.VISIBLE);
                testPerformanceAttempts.setVisibility(View.GONE);
                testPerformanceBackBtn.setVisibility(View.GONE);

            }
        });

        recentActivityPerformanceBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drillDownActivityLog.setVisibility(View.VISIBLE);
                recAcivity.setVisibility(View.VISIBLE);
                recentActivityPerformance.setVisibility(View.GONE);
                recentActivityPerformanceBackBtn.setVisibility(View.GONE);

            }
        });


    }


    void getDashBoard() {

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getContext());

        Call<PieChartResponse> chartResponseCall = retrofitService.gettestsummary(sessionManager.getOrgId());

        chartResponseCall.enqueue(new Callback<PieChartResponse>() {
            @Override
            public void onResponse(Call<PieChartResponse> call, Response<PieChartResponse> response) {

                if (response.isSuccessful()) {

                    testSummaryList = response.body().getTestSummary();

                    testGroupList = response.body().getTestGroup();

                    performanceAnalysiList = response.body().getPerformanceAnalysis();

                    recentActivityLogList = response.body().getRecentActivityLog();

                    Log.e("summary", String.valueOf(testSummaryList.size()));

                    Log.e("group", String.valueOf(testGroupList.size()));

                    Log.e("performance", String.valueOf(performanceAnalysiList.size()));

                    Log.e("recent", String.valueOf(recentActivityLogList.size()));

                    // this condition for check the size of list. If it is 0 - execute the statement otherwise execute the else block

                    if (testSummaryList.size() == 0 && testGroupList.size() == 0 && performanceAnalysiList.size() == 0 && recentActivityLogList.size() == 0) {

                        performanceSummaryTv.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                        noDataIcon.setVisibility(View.VISIBLE);
                        noDataTv.setVisibility(View.VISIBLE);

                    } else {

                        performanceSummaryTv.setVisibility(View.VISIBLE);
                        testSummaryEntry();
                        testGroupEntry();
                        testPerformance();
                        testRecentActivity();

                        progressBar.setVisibility(View.GONE);
                    }
                } else {

                    performanceSummaryTv.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);

                    Log.e("error code", String.valueOf(response.code()));
                    Log.e("error msg", String.valueOf(response.message()));

                    // error handling

                    if (response.code() == 404) {

                        binding.pageNotFoundIcon.setVisibility(View.VISIBLE);

                        new AlertDialog.Builder(getContext()).
                                setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                }).setCancelable(false).setMessage("404, Not Found").create().show();

                    } else if (response.code() == 401) {
                        new AlertDialog.Builder(getContext()).
                                setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        sessionManager.logoutUser();
                                    }
                                }).setCancelable(false).setMessage("401, Unauthorized error").create().show();

                    } else if (response.code() == 400) {

                        binding.badRequestIconDashboard.setVisibility(View.VISIBLE);

                        new AlertDialog.Builder(getContext()).
                                setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).setCancelable(false).setMessage("400, Bad Request Error").create().show();

                    } else {

                        binding.serverErrorIconDashboard.setVisibility(View.VISIBLE);
                        new AlertDialog.Builder(getContext()).
                                setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).setCancelable(false).setMessage("500, Server takes too long to respond").create().show();
                    }
                }
            }

            @Override
            public void onFailure(Call<PieChartResponse> call, Throwable t) {

                Log.e(TAG, "onFailure " + t.getCause());
                Log.e(TAG, "onFailure " + t.getMessage());
            }
        });
    }

    private void testSummaryEntry() {

        // test summary pie chart code here

        if (testSummaryList.size() > 0) {
            binding.drillDownTestSummary.setVisibility(View.VISIBLE);
            testSummaryTv.setVisibility(View.VISIBLE);
            pieChart.setVisibility(View.VISIBLE);
        }

        pieChart.getDescription().setEnabled(false);
        //pieChart.getDescription().setTextSize(13f);
        // pieChart.setEntryLabelTextSize(7);
        pieChart.setDrawEntryLabels(false);

        List<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < testSummaryList.size(); i++) {

            entries.add(new PieEntry(testSummaryList.get(i).getNoOfTests(), testSummaryList.get(i).getTestStatus()));

        }

        PieDataSet set = new PieDataSet(entries, "");

        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(set);
        pieChart.animateX(2000);
        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                pieChart.setVisibility(View.GONE);
                binding.drillDownTestSummary.setVisibility(View.GONE);
                backButton.setVisibility(View.VISIBLE);
                binding.testSummaryClickRecView.setVisibility(View.VISIBLE);
                PieEntry pe = (PieEntry) e;
                Log.e("LABEL", pe.getLabel());
                Const.testSummaryStatus = pe.getLabel();
                testSummaryDesc();

            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void testSummaryDesc() {

        // test summary onclick api call

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getContext());

        Call<List<TestDetailsDrilResponse>> listCall = retrofitService.getTestDetailsDrill(sessionManager.getOrgId(), Const.testSummaryStatus);

        listCall.enqueue(new Callback<List<TestDetailsDrilResponse>>() {

            @Override
            public void onResponse(Call<List<TestDetailsDrilResponse>> call, Response<List<TestDetailsDrilResponse>> response) {
                if (response.isSuccessful()) {

                    List<TestDetailsDrilResponse> testDetailsDrilResponse = response.body();

                    TestDetailsDrillAdaper adaper = new TestDetailsDrillAdaper(testDetailsDrilResponse, getContext());

                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

                    binding.testSummaryClickRecView.setLayoutManager(layoutManager);

                    binding.testSummaryClickRecView.setAdapter(adaper);

                }
            }

            @Override
            public void onFailure(Call<List<TestDetailsDrilResponse>> call, Throwable t) {

            }
        });


    }

    private void testGroupEntry() {

        // test vs group pie chart code here

        if (testGroupList.size() > 0) {
            binding.drillDownTestGroup.setVisibility(View.VISIBLE);
            testGroupTv.setVisibility(View.VISIBLE);
            testGroupPieChart.setVisibility(View.VISIBLE);
        }

        testGroupPieChart.getDescription().setEnabled(false);
        // testGroupPieChart.setEntryLabelTextSize(7);
        testGroupPieChart.setDrawEntryLabels(false);
        //testGroupPieChart.getDescription().setTextSize(13f);

        List<PieEntry> testgrouppieEntries = new ArrayList<>();

        for (int i = 0; i < testGroupList.size(); i++) {
            testgrouppieEntries.add(new PieEntry(testGroupList.get(i).getNoOfTests(), testGroupList.get(i).getGrpName(), testGroupList.get(i).grpId));
        }

        PieDataSet set = new PieDataSet(testgrouppieEntries, "");
        Legend legend = testGroupPieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

        set.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData testgroupdata = new PieData(set);
        testGroupPieChart.animateY(2000);
        testGroupPieChart.setData(testgroupdata);
        testGroupPieChart.invalidate();

        testGroupPieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                binding.drillDownTestGroup.setVisibility(View.GONE);

                PieEntry pe = (PieEntry) e;
                Log.e("eee", String.valueOf(pe.getData()));

                Const.testGroup = pe.getData();

                testGroupDesc();

            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    void testGroupDesc() {

        // test vs group onclick api call

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getContext());

        Call<List<TestGroupDrillResponse>> listCall = retrofitService.getTestGroupDrill(sessionManager.getOrgId(), Const.testGroup);

        listCall.enqueue(new Callback<List<TestGroupDrillResponse>>() {
            @Override
            public void onResponse(Call<List<TestGroupDrillResponse>> call, Response<List<TestGroupDrillResponse>> response) {

                List<TestGroupDrillResponse> testGroupDrillResponse = response.body();

                testGroupDrillResponseList = testGroupDrillResponse;

                testPerformanceTestGroup();

            }

            @Override
            public void onFailure(Call<List<TestGroupDrillResponse>> call, Throwable t) {

            }
        });
    }

    private void testPerformanceTestGroup() {

        // test vs group onclick performance bar chart code here

        if (testGroupDrillResponseList.size() > 0) {
            performanceAnalysiTv.setVisibility(View.VISIBLE);
            testGroupPieChart.setVisibility(View.GONE);
            testGroupBackBtn.setVisibility(View.VISIBLE);
            testGroupTestPerformance.setVisibility(View.VISIBLE);
        } else {

        }

        if (testGroupDrillResponseList.size() == 0) {

            testGroupPieChart.setVisibility(View.GONE);
            testGroupBackBtn.setVisibility(View.VISIBLE);
            binding.testGroupNoDataIcon.setVisibility(View.VISIBLE);


        }

        testGroupTestPerformance.getDescription().setText(" ");
        testGroupTestPerformance.getDescription().setTextSize(13f);

        List<BarEntry> barEntries = new ArrayList<>();

        for (int i = 0; i < testGroupDrillResponseList.size(); i++) {

            barEntries.add(new BarEntry(i, (Float.parseFloat(testGroupDrillResponseList.get(i).getUserScore())), testGroupDrillResponseList.get(i).getTestName()));
        }

        Legend legend = testGroupTestPerformance.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

        XAxis xAxis = testGroupTestPerformance.getXAxis();
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setEnabled(false);
        testGroupTestPerformance.setDrawValueAboveBar(true);
        testGroupTestPerformance.setDoubleTapToZoomEnabled(false);
        testGroupTestPerformance.setPinchZoom(false);
        testGroupTestPerformance.setScaleEnabled(false);
        YAxis left = testGroupTestPerformance.getAxisLeft();
        left.setDrawZeroLine(true);
        left.setDrawGridLines(false);
        left.setSpaceTop(30f);
        testGroupTestPerformance.animateX(3000);
        testGroupTestPerformance.getAxisRight().setEnabled(false);
        testGroupTestPerformance.getAxisLeft().setEnabled(true);
        BarDataSet barDataSet = new BarDataSet(barEntries, "User Score");
        barDataSet.setColor(ContextCompat.getColor((getContext()), R.color.color_user_score_green));
        BarData data = new BarData(barDataSet);
        testGroupTestPerformance.setData(data);
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return entry.getData().toString();
            }

        });

    }

    private void testRecentActivity() {

        // recent activity log line chart code here

        if (recentActivityLogList.size() > 0) {
            binding.drillDownActivityLog.setVisibility(View.VISIBLE);
            recentActivityLogTv.setVisibility(View.VISIBLE);
            recAcivity.setVisibility(View.VISIBLE);
        }

        recAcivity.getDescription().setEnabled(false);
        //recAcivity.getDescription().setTextSize(13f);

        List<Entry> entriesLine = new ArrayList<>();
        for (int i = 0; i < recentActivityLogList.size(); i++) {
            entriesLine.add(new Entry(i, (Float.parseFloat(recentActivityLogList.get(i).getPercentScore())), recentActivityLogList.get(i).testInstanceId));


            LineDataSet lineDataSet = new LineDataSet(entriesLine, "Percent Score");

            lineDataSet.setDrawValues(false);
            Legend legend = recAcivity.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

            YAxis left = recAcivity.getAxisLeft();
            XAxis xAxis = recAcivity.getXAxis();
            xAxis.setGranularity(1f);
          //  xAxis.setCenterAxisLabels(true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setEnabled(true);
            xAxis.setDrawLabels(true);
            xAxis.setLabelCount(6,true);
            xAxis.setTextSize(7);
            left.setDrawLabels(true);
            left.setDrawAxisLine(true);
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            recAcivity.getAxisLeft().setEnabled(true);
            recAcivity.getAxisRight().setEnabled(false);
            recAcivity.animateX(1000);
            recAcivity.setDoubleTapToZoomEnabled(false);
            recAcivity.setPinchZoom(false);
            recAcivity.setScaleEnabled(false);
            recAcivity.setPinchZoom(false);
            recAcivity.setScaleEnabled(false);
            LineData lineData = new LineData(lineDataSet);
            recAcivity.setData(lineData);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(recentActivity()));
        }
        recAcivity.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Const.testRecentInstanceId = e.getData();

                binding.drillDownActivityLog.setVisibility(View.GONE);

                Log.e("instance", String.valueOf(Const.testRecentInstanceId));
                recAcivity.setVisibility(View.GONE);
                recentActivityPerformanceBackBtn.setVisibility(View.VISIBLE);

                testRecentActivityDesc();

            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    void testRecentActivityDesc() {

        // recent activity log onclick api call

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getContext());

        Call<List<TestRecentActivityDrillResponse>> listCall = retrofitService.getRecentActivityDrill(sessionManager.getOrgId(), Const.testRecentInstanceId);

        listCall.enqueue(new Callback<List<TestRecentActivityDrillResponse>>() {
            @Override
            public void onResponse(Call<List<TestRecentActivityDrillResponse>> call, Response<List<TestRecentActivityDrillResponse>> response) {

                if (response.isSuccessful()) {

                    List<TestRecentActivityDrillResponse> testRecentActivityDrillResponses = response.body();

                    testRecentActivityDrillResponseList = testRecentActivityDrillResponses;

                    testRecentActivityPerformance();
                }

            }

            @Override
            public void onFailure(Call<List<TestRecentActivityDrillResponse>> call, Throwable t) {

            }
        });
    }

    private void testRecentActivityPerformance() {

        // recent activity log onclick performanc bar chart code here

        if (testRecentActivityDrillResponseList.size() > 0) {
            performanceAnalysiTv.setVisibility(View.VISIBLE);
            recentActivityPerformance.setVisibility(View.VISIBLE);
        }

        recentActivityPerformance.getDescription().setText(" ");
        recentActivityPerformance.getDescription().setTextSize(13f);

        List<BarEntry> barEntries = new ArrayList<>();

        for (int i = 0; i < testRecentActivityDrillResponseList.size(); i++) {

            barEntries.add(new BarEntry(i, (Float.parseFloat(testRecentActivityDrillResponseList.get(i).getPercentageScore()))));

            Legend legend = recentActivityPerformance.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

            XAxis xAxis = recentActivityPerformance.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setEnabled(true);
            //xAxis.setCenterAxisLabels(true);
            xAxis.setLabelCount(i);
            recentActivityPerformance.setDrawValueAboveBar(true);
            recentActivityPerformance.setDoubleTapToZoomEnabled(false);
            recentActivityPerformance.setPinchZoom(false);
            recentActivityPerformance.setScaleEnabled(false);
            YAxis left = recentActivityPerformance.getAxisLeft();
            left.setDrawLabels(true);
            left.setDrawAxisLine(true);
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            recentActivityPerformance.animateX(1000);
            recentActivityPerformance.getAxisRight().setEnabled(true);
            recentActivityPerformance.getAxisLeft().setEnabled(false);
            BarDataSet barDataSet = new BarDataSet(barEntries, "Percent Score");
            barDataSet.setColor(ContextCompat.getColor((getContext()), R.color.color_user_score_green));
            BarData data = new BarData(barDataSet);
            xAxis.setAxisMaximum(i + 1);
            recentActivityPerformance.setData(data);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(activityLog()));

        }
    }

    private ArrayList<String> activityLog() {

        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < testRecentActivityDrillResponseList.size(); i++)
            label.add(testRecentActivityDrillResponseList.get(i).getTestDtl());
        return label;
    }

    private ArrayList<String> recentActivity() {

        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < recentActivityLogList.size(); i++) {
            label.add(recentActivityLogList.get(i).getTestDate());
        }
        return label;
    }

    private void testPerformance() {

        // performance analysis bar chart code here

        if (performanceAnalysiList.size() > 0) {
            binding.drillDownPerformanceAnalysis.setVisibility(View.VISIBLE);
            performanceAnalysiTv.setVisibility(View.VISIBLE);
            barChart.setVisibility(View.VISIBLE);
        }

        barChart.getDescription().setText(" ");
        barChart.getDescription().setTextSize(13f);

        List<BarEntry> barEntries = new ArrayList<>();

        for (int i = 0; i < performanceAnalysiList.size(); i++) {

            barEntries.add(new BarEntry(i, (Float.parseFloat(performanceAnalysiList.get(i).getUserScore())), performanceAnalysiList.get(i).getTestId()));


            Legend legend = barChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

            XAxis xAxis = barChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setEnabled(true);

            xAxis.setLabelCount(i);
           // xAxis.setCenterAxisLabels(true);
            barChart.setDoubleTapToZoomEnabled(false);
            barChart.setPinchZoom(false);
            YAxis left = barChart.getAxisLeft();
            left.setDrawLabels(true);
            left.setDrawAxisLine(true);
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            barChart.animateX(1000);
            barChart.getAxisRight().setEnabled(false);
            barChart.getAxisLeft().setEnabled(true);
            BarDataSet barDataSet = new BarDataSet(barEntries, "User Score");
            barDataSet.setColor(ContextCompat.getColor((getContext()), R.color.color_user_score_green));
            BarData data = new BarData(barDataSet);
            barChart.setData(data);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(getPerformance()));
        }
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Const.testPerformanceTestId = e.getData();
                Log.e("grgid", String.valueOf(Const.testPerformanceTestId));
                barChart.setVisibility(View.GONE);
                binding.drillDownPerformanceAnalysis.setVisibility(View.GONE);
                testPerformanceBackBtn.setVisibility(View.VISIBLE);
                TestPerformanceDesc();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private ArrayList<String> getPerformance() {

        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < performanceAnalysiList.size(); i++) {
            label.add(performanceAnalysiList.get(i).getTestName());
        }
        return label;
    }

    void TestPerformanceDesc() {

        // Performance analysis log onclick api call

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getContext());

        Call<List<TestPerformanceDrillResponse>> listCall = retrofitService.getTestPerformanceDrill(sessionManager.getOrgId(), Const.testPerformanceTestId);

        listCall.enqueue(new Callback<List<TestPerformanceDrillResponse>>() {
            @Override
            public void onResponse(Call<List<TestPerformanceDrillResponse>> call, Response<List<TestPerformanceDrillResponse>> response) {

                if (response.isSuccessful()) {

                    List<TestPerformanceDrillResponse> testPerformanceDrillResponses = response.body();

                    testPerformanceDrillResponseList = testPerformanceDrillResponses;

                    testPerformanceAttempts();
                }
            }

            @Override
            public void onFailure(Call<List<TestPerformanceDrillResponse>> call, Throwable t) {

            }
        });
    }

    private void testPerformanceAttempts() {

        // performance analysis onclick performanc bar chart code here

        if (testPerformanceDrillResponseList.size() > 0) {
            recentActivityLogTv.setVisibility(View.VISIBLE);
            testPerformanceAttempts.setVisibility(View.VISIBLE);
        }

        testPerformanceAttempts.getDescription().setText(" ");
        testPerformanceAttempts.getDescription().setTextSize(13f);

        List<Entry> entriesLine = new ArrayList<>();
        for (int i = 0; i < testPerformanceDrillResponseList.size(); i++) {
            entriesLine.add(new Entry(i, (Float.parseFloat(testPerformanceDrillResponseList.get(i).getPercentageScore()))));
            LineDataSet lineDataSet = new LineDataSet(entriesLine, "Present Score");

            Legend legend = testPerformanceAttempts.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

            YAxis left = testPerformanceAttempts.getAxisLeft();
            XAxis xAxis = testPerformanceAttempts.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setEnabled(true);
            left.setDrawLabels(true);
            left.setDrawAxisLine(true);
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            testPerformanceAttempts.getAxisLeft().setEnabled(true);
            testPerformanceAttempts.getAxisRight().setEnabled(false);
            testPerformanceAttempts.animateX(3000);
            testPerformanceAttempts.setDoubleTapToZoomEnabled(false);
            LineData lineData = new LineData(lineDataSet);
            testPerformanceAttempts.setData(lineData);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(performancerecentActivity()));

        }
    }

    private ArrayList<String> performancerecentActivity() {
        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < testPerformanceDrillResponseList.size(); i++) {
            label.add(testPerformanceDrillResponseList.get(i).getAttempt());
        }
        return label;
    }


}