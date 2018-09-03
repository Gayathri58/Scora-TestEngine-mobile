package com.brigita.dashboard.pika.performance_insights;


import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.brigita.dashboard.pika.Const.Const;
import com.brigita.dashboard.pika.R;
import com.brigita.dashboard.pika.SessionManager;
import com.brigita.dashboard.pika.performance_insights.adapter.TestScoreBoardAdapter;
import com.brigita.dashboard.pika.performance_insights.model.DifficultyLevelCountRadio;
import com.brigita.dashboard.pika.performance_insights.model.DifficultyLevelResponse;
import com.brigita.dashboard.pika.performance_insights.model.DifficultyLevelScoreRadio;
import com.brigita.dashboard.pika.performance_insights.model.StrongAreaRadio;
import com.brigita.dashboard.pika.performance_insights.model.StrongWeakAreaResponse;
import com.brigita.dashboard.pika.performance_insights.model.TaxonomyLevelCountRadio;
import com.brigita.dashboard.pika.performance_insights.model.TaxonomyLevelResponse;
import com.brigita.dashboard.pika.performance_insights.model.TaxonomyLevelScoreRadio;
import com.brigita.dashboard.pika.performance_insights.model.TestAttempt;
import com.brigita.dashboard.pika.performance_insights.model.TestAttemptResponse;
import com.brigita.dashboard.pika.performance_insights.model.TestNameResponse;
import com.brigita.dashboard.pika.performance_insights.model.TestScoreBoardResponse;
import com.brigita.dashboard.pika.performance_insights.model.TopicCountRadio;
import com.brigita.dashboard.pika.performance_insights.model.TopicLevelResponse;
import com.brigita.dashboard.pika.performance_insights.model.TopicScoreRadio;
import com.brigita.dashboard.pika.performance_insights.model.WeakAreaRadio;
import com.brigita.dashboard.pika.databinding.FragmentPerformanceInsightsBinding;
import com.brigita.dashboard.pika.retrofit.RetrofitService;
import com.brigita.dashboard.pika.retrofit.RetrofitServiceGenerator;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerformanceInsightsFragment extends Fragment {

    private static final String TAG = PerformanceInsightsFragment.class.getSimpleName();

    FragmentPerformanceInsightsBinding binding;
    private SessionManager sessionManager;

    Spinner testNameSpinner;
    Spinner attemptSpinner;

    TextView difficultyTv;
    TextView difficultyCount;
    TextView difficultyScore;
    TextView taxonomyTv;
    TextView taxonomyCount;
    TextView taxnomyScore;
    TextView topicTv;
    TextView topicCount;
    TextView topicScore;
    TextView strongWeakTv;
    TextView strongArea;
    TextView weakArea;
    TextView noDataTv;

    BarChart difficultyCountBarChart;
    BarChart difficultyScoreBarChart;
    BarChart taxonomyCountBarChart;
    BarChart taxnomyScoreBarChart;
    BarChart topicCountBarChart;
    BarChart topicScoreBarChart;
    BarChart strongAreaBarChart;
    BarChart weakAreaBarChart;

    ImageView insufficientDataCount;
    ImageView insufficientDataScore;
    ImageView taxoCountIcon;
    ImageView taxScoreIcon;
    ImageView topicCountIcon;
    ImageView topicScoreIcon;
    ImageView strongIcon;
    ImageView weakIcon;
    ImageView noDataInsightsIcon ;

    ProgressBar progressBar;

    ImageView noDataIcon;

    List<TestAttempt> testAttemptList;
    List<DifficultyLevelCountRadio> difficultyLevelCountRadioList;
    List<DifficultyLevelScoreRadio> difficultyLevelScoreRadioList;
    List<TaxonomyLevelCountRadio> taxonomyLevelCountRadioList;
    List<TaxonomyLevelScoreRadio> taxonomyLevelScoreRadioList;
    List<TopicCountRadio> topicCountRadioList;
    List<TopicScoreRadio> topicScoreRadioList;
    List<StrongAreaRadio> strongAreaRadioList;
    List<WeakAreaRadio> weakAreaRadioList;


    public PerformanceInsightsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_performance_insights, container, false);

        testNameSpinner = binding.spinnerTestName.findViewById(R.id.spinner_test_name);
        attemptSpinner = binding.spinnerAttemptCount.findViewById(R.id.spinner_attempt_count);

        noDataInsightsIcon =  binding.noDataInsghts.findViewById(R.id.no_data_insghts);
        noDataTv = binding.noDataHistoryTv.findViewById(R.id.no_data_history_tv);

        progressBar = binding.progressBarInsights.findViewById(R.id.progress_bar_insights);
        noDataIcon = binding.scoreBoardIcon.findViewById(R.id.score_board_icon);

        difficultyTv = binding.insightsDifficultyTv.findViewById(R.id.insights_difficulty_tv);
        taxonomyTv = binding.insightsTaxonomyTv.findViewById(R.id.insights_taxonomy_tv);
        topicTv = binding.insightsTopicTv.findViewById(R.id.insights_topic_tv);
        strongWeakTv = binding.insightsStrongWeakTv.findViewById(R.id.insights_strong_weak_tv);

        difficultyCount = binding.insightsDifficultyCountTv.findViewById(R.id.insights_difficulty_count_tv);
        difficultyCountBarChart = binding.insightsDifficultyLevelCountChart.findViewById(R.id.insights_difficulty_level_count_chart);
        insufficientDataCount = binding.difficultyCountIcon.findViewById(R.id.difficulty_count_icon);

        difficultyScore = binding.insightsDifficultyScoreTv.findViewById(R.id.insights_difficulty_score_tv);
        difficultyScoreBarChart = binding.insightsDifficultyLevelScoreChart.findViewById(R.id.insights_difficulty_level_score_chart);
        insufficientDataScore = binding.difficultyScoreIcon.findViewById(R.id.difficulty_score_icon);

        taxonomyCount = binding.insightsTaxonomyCountTv.findViewById(R.id.insights_taxonomy_count_tv);
        taxonomyCountBarChart = binding.insightsTaxonomyLevelCountChart.findViewById(R.id.insights_taxonomy_level_count_chart);
        taxoCountIcon = binding.taxonomyCountIcon.findViewById(R.id.taxonomy_count_icon);

        taxnomyScore = binding.insightsTaxonomyScoreTv.findViewById(R.id.insights_taxonomy_score_tv);
        taxnomyScoreBarChart = binding.insightsTaxonomyLevelScoreChart.findViewById(R.id.insights_taxonomy_level_score_chart);
        taxScoreIcon = binding.taxonomyScoreIcon.findViewById(R.id.taxonomy_score_icon);

        topicCount = binding.insightsTopicCountTv.findViewById(R.id.insights_topic_count_tv);
        topicCountBarChart = binding.insightsTopicLevelCountChart.findViewById(R.id.insights_topic_level_count_chart);
        topicCountIcon = binding.topicCountIcon.findViewById(R.id.topic_count_icon);

        topicScore = binding.insightsTopicScoreTv.findViewById(R.id.insights_topic_score_tv);
        topicScoreBarChart = binding.insightsTopicLevelScoreChart.findViewById(R.id.insights_topic_level_score_chart);
        topicScoreIcon = binding.topicScoreIcon.findViewById(R.id.topic_score_icon);

        strongArea = binding.insightsStrongAreaTv.findViewById(R.id.insights_strong_area_tv);
        strongAreaBarChart = binding.insightsStrongAreaChart.findViewById(R.id.insights_strong_area_chart);
        strongIcon = binding.strongIcon.findViewById(R.id.strong_icon);

        weakArea = binding.insightsWeakAreaTv.findViewById(R.id.insights_weak_area_tv);
        weakAreaBarChart = binding.insightsWeakAreaChart.findViewById(R.id.insights_weak_area_chart);
        weakIcon = binding.weakIcon.findViewById(R.id.weak_icon);


        // Inflate the layout for this fragment
        return binding.getRoot();
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

        getTestName();

        difficultyLevel();
        taxonomyLevel();
        topicLevel();
        strongWeakArea();
        testScoreBoard();

    }

    void testScoreBoard() {

        // test score board api call code here

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getContext());

        Call<List<TestScoreBoardResponse>> listCall = retrofitService.getTestScoreBoard(sessionManager.getOrgId(), Const.attempt_Test_id, Const.attempt_Attempt_Count);

        listCall.enqueue(new Callback<List<TestScoreBoardResponse>>() {
            @Override
            public void onResponse(Call<List<TestScoreBoardResponse>> call, Response<List<TestScoreBoardResponse>> response) {

                if (response.isSuccessful()) {

                    List<TestScoreBoardResponse> testScoreBoardResponses = response.body();

                   /* if (testScoreBoardResponses == null ) {

                        binding.insightsScoreBoardLable.setVisibility(View.GONE);
                    }

                    binding.insightsScoreBoardLable.setVisibility(View.VISIBLE);*/

                    TestScoreBoardAdapter adapter = new TestScoreBoardAdapter(testScoreBoardResponses, getContext());

                    LinearLayoutManager manager = new LinearLayoutManager(getContext());

                    binding.scoreboardTableRv.setLayoutManager(manager);
                    binding.scoreboardTableRv.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<TestScoreBoardResponse>> call, Throwable t) {

            }
        });
    }

    void strongWeakArea() {

        // strong and weak area api call code here

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getContext());

        Call<StrongWeakAreaResponse> strongWeakAreaResponseCall = retrofitService.getStrongWeakArea(sessionManager.getOrgId(), Const.attempt_Test_id, Const.attempt_Attempt_Count);
        strongWeakAreaResponseCall.enqueue(new Callback<StrongWeakAreaResponse>() {
            @Override
            public void onResponse(Call<StrongWeakAreaResponse> call, Response<StrongWeakAreaResponse> response) {

                if (response.isSuccessful()) {

                    StrongWeakAreaResponse strongWeakAreaResponse = response.body();

                    strongAreaRadioList = strongWeakAreaResponse.getStrongArea();
                    weakAreaRadioList = strongWeakAreaResponse.getWeakArea();


                    getstrongArea();
                    getweakArea();

                }
            }

            @Override
            public void onFailure(Call<StrongWeakAreaResponse> call, Throwable t) {

            }
        });

    }

    void getstrongArea() {

        // strong area bar chart code here

        if (strongAreaRadioList.size() > 0) {
            strongIcon.setVisibility(View.GONE);
            strongWeakTv.setVisibility(View.VISIBLE);
            strongArea.setVisibility(View.VISIBLE);
            strongAreaBarChart.setVisibility(View.VISIBLE);
        } else {
            strongAreaBarChart.setVisibility(View.GONE);
            strongIcon.setVisibility(View.VISIBLE);
        }

        strongAreaBarChart.getDescription().setText(" ");
        strongAreaBarChart.getDescription().setTextSize(13f);

        List<BarEntry> entries1 = new ArrayList<>();

        for (int i = 0; i < strongAreaRadioList.size(); i++) {
            entries1.add(new BarEntry(i, Float.parseFloat(strongAreaRadioList.get(i).getCorrect())));

            BarDataSet set2 = new BarDataSet(entries1, "Correct");

            XAxis xAxis = strongAreaBarChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setDrawLabels(true);

            //final String[] strongArea = {"Physical Database Design", "Object-Oriented Database"};

            //xAxis.setLabelCount(4, true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            YAxis left = strongAreaBarChart.getAxisLeft();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(getstrongAreaCount()));
            xAxis.setEnabled(true);

            left.setDrawLabels(true);
            left.setDrawAxisLine(true);
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            strongAreaBarChart.getAxisLeft().setEnabled(true);
            strongAreaBarChart.getAxisRight().setEnabled(false);

            strongAreaBarChart.setDrawValueAboveBar(true);
            strongAreaBarChart.setDoubleTapToZoomEnabled(false);
            strongAreaBarChart.setPinchZoom(false);
            strongAreaBarChart.setScaleEnabled(false);
            set2.setColor(ContextCompat.getColor(getContext(), R.color.color_corrent_blue));

            Legend legend = strongAreaBarChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            BarData data = new BarData(set2);
            strongAreaBarChart.setData(data);
            strongAreaBarChart.invalidate();
            strongAreaBarChart.animateX(2000);
        }

    }

    private ArrayList<String> getstrongAreaCount() {
        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < strongAreaRadioList.size(); i++)
            label.add(strongAreaRadioList.get(i).getTopicNm());
        return label;

    }

    void getweakArea() {

        // weak area bar chart code here

        if (weakAreaRadioList.size() > 0) {
            weakIcon.setVisibility(View.GONE);
            weakArea.setVisibility(View.VISIBLE);
            strongWeakTv.setVisibility(View.VISIBLE);
            weakAreaBarChart.setVisibility(View.VISIBLE);
        } else {
            weakAreaBarChart.setVisibility(View.GONE);
            weakIcon.setVisibility(View.VISIBLE);
        }

        weakAreaBarChart.getDescription().setText(" ");
        weakAreaBarChart.getDescription().setTextSize(13f);

        List<BarEntry> entries1 = new ArrayList<>();

        for (int i = 0; i < weakAreaRadioList.size(); i++) {
            entries1.add(new BarEntry(i, Float.parseFloat(weakAreaRadioList.get(i).getIncorrect())));

            BarDataSet set2 = new BarDataSet(entries1, "Incorrect");

            XAxis xAxis = weakAreaBarChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setDrawLabels(true);

            //final String[] strongArea = {"Physical Database Design", "Object-Oriented Database"};

            //xAxis.setLabelCount(4, true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            YAxis left = weakAreaBarChart.getAxisLeft();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(getweakAreaCount()));
            xAxis.setEnabled(true);

            left.setDrawLabels(true);
            left.setDrawAxisLine(true);
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            weakAreaBarChart.getAxisLeft().setEnabled(true);
            weakAreaBarChart.getAxisRight().setEnabled(false);

            weakAreaBarChart.setDrawValueAboveBar(true);
            weakAreaBarChart.setDoubleTapToZoomEnabled(false);
            weakAreaBarChart.setPinchZoom(false);
            weakAreaBarChart.setScaleEnabled(false);
            set2.setColor(ContextCompat.getColor(getContext(), R.color.color_incorrent_pink));

            Legend legend = weakAreaBarChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            BarData data = new BarData(set2);
            weakAreaBarChart.setData(data);
            weakAreaBarChart.invalidate();
            weakAreaBarChart.animateX(2000);
        }

    }

    private ArrayList<String> getweakAreaCount() {

        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < weakAreaRadioList.size(); i++)
            label.add(weakAreaRadioList.get(i).getTopicNm());
        return label;

    }

    void topicLevel() {

        // topic api call code here

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getContext());

        Call<TopicLevelResponse> topicLevelResponseCall = retrofitService.getTopicLevel(sessionManager.getOrgId(), Const.attempt_Test_id, Const.attempt_Attempt_Count);

        topicLevelResponseCall.enqueue(new Callback<TopicLevelResponse>() {
            @Override
            public void onResponse(Call<TopicLevelResponse> call, Response<TopicLevelResponse> response) {

                if (response.isSuccessful()) {

                    TopicLevelResponse topicLevelResponse = response.body();

                    topicCountRadioList = topicLevelResponse.getTopicCount();
                    topicScoreRadioList = topicLevelResponse.getTopicScore();

                    gettopicCount();
                    gettopicScore();

                }

            }

            @Override
            public void onFailure(Call<TopicLevelResponse> call, Throwable t) {

            }
        });
    }

    private void gettopicCount() {

        // topic count bar chart code here

        if (topicCountRadioList.size() > 0) {
            topicCountIcon.setVisibility(View.GONE);
            topicTv.setVisibility(View.VISIBLE);
            topicCount.setVisibility(View.VISIBLE);
            topicCountBarChart.setVisibility(View.VISIBLE);
        } else {
            topicCountBarChart.setVisibility(View.GONE);
            topicCountIcon.setVisibility(View.VISIBLE);
        }
        topicCountBarChart.getDescription().setText("");
        topicCountBarChart.getDescription().setTextSize(13f);

        List<BarEntry> entries = new ArrayList<>();
        List<BarEntry> entries1 = new ArrayList<>();
        List<BarEntry> entries2 = new ArrayList<>();

        for (int i = 0; i < topicCountRadioList.size(); i++) {

            entries.add(new BarEntry(i, Float.parseFloat(topicCountRadioList.get(i).getIncorrect())));
            entries1.add(new BarEntry(i, Float.parseFloat(topicCountRadioList.get(i).getCorrect())));
            entries2.add(new BarEntry(i, Float.parseFloat(topicCountRadioList.get(i).getMissed())));

            Log.e("incorrent", topicCountRadioList.get(i).getIncorrect());

            BarDataSet set1 = new BarDataSet(entries, "Incorrect");
            BarDataSet set2 = new BarDataSet(entries1, "Correct");
            BarDataSet set3 = new BarDataSet(entries2, "Missed");

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            dataSets.add(set2);
            dataSets.add(set3);

            float groupSpace = 0.20f;
            float barSpace = 0.09f;
            float barWidth = 0.17f;

            XAxis xAxis = topicCountBarChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setDrawLabels(true);

            //xAxis.setLabelCount(4, true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            YAxis left = topicCountBarChart.getAxisLeft();
            xAxis.setEnabled(true);
            xAxis.setCenterAxisLabels(true);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(getTopicCountValue()));

           /* left.setDrawLabels(true);
            left.setDrawAxisLine(true);*/
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            topicCountBarChart.getAxisLeft().setEnabled(true);
            topicCountBarChart.getAxisRight().setEnabled(false);

            topicCountBarChart.setDrawValueAboveBar(true);
            topicCountBarChart.setDoubleTapToZoomEnabled(false);
            topicCountBarChart.setPinchZoom(false);
            topicCountBarChart.setScaleEnabled(false);
            set1.setColor(ContextCompat.getColor(getActivity(), R.color.color_incorrent_pink));
            set2.setColor(ContextCompat.getColor(getActivity(), R.color.color_corrent_blue));
            set3.setColor(ContextCompat.getColor(getActivity(), R.color.color_missed_orange));

            Legend legend = topicCountBarChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            BarData data = new BarData(dataSets);
            data.setBarWidth(barWidth);
            topicCountBarChart.setData(data);
            topicCountBarChart.invalidate();
            xAxis.setAxisMaximum(i + 1);
            topicCountBarChart.groupBars(0f, groupSpace, barSpace);
            topicCountBarChart.animateX(2000);
        }

    }

    private ArrayList<String> getTopicCountValue() {
        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < topicCountRadioList.size(); i++)
            label.add(topicCountRadioList.get(i).getTopicNm());
        return label;
    }

    private void gettopicScore() {

        // topic score bar chart code here

        if (topicScoreRadioList.size() > 0) {
            topicScoreIcon.setVisibility(View.GONE);
            topicTv.setVisibility(View.VISIBLE);
            topicScore.setVisibility(View.VISIBLE);
            topicScoreBarChart.setVisibility(View.VISIBLE);
        } else {
            topicScoreBarChart.setVisibility(View.GONE);
            topicScoreIcon.setVisibility(View.VISIBLE);
        }
        topicScoreBarChart.getDescription().setText("");
        topicScoreBarChart.getDescription().setTextSize(13f);

        List<BarEntry> entries = new ArrayList<>();
        List<BarEntry> entries1 = new ArrayList<>();
        List<BarEntry> entries2 = new ArrayList<>();

        for (int i = 0; i < topicScoreRadioList.size(); i++) {

            entries.add(new BarEntry(i, Float.parseFloat(topicScoreRadioList.get(i).getIncorrect())));
            entries1.add(new BarEntry(i, Float.parseFloat(topicScoreRadioList.get(i).getCorrect())));
            entries2.add(new BarEntry(i, Float.parseFloat(topicScoreRadioList.get(i).getMissed())));

            Log.e("incorrent", topicScoreRadioList.get(i).getIncorrect());

            BarDataSet set1 = new BarDataSet(entries, "Incorrect");
            BarDataSet set2 = new BarDataSet(entries1, "Correct");
            BarDataSet set3 = new BarDataSet(entries2, "Missed");

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            dataSets.add(set2);
            dataSets.add(set3);

            float groupSpace = 0.20f;
            float barSpace = 0.09f;
            float barWidth = 0.17f;

            XAxis xAxis = topicScoreBarChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setDrawLabels(true);

            //xAxis.setLabelCount(4, true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            YAxis left = topicScoreBarChart.getAxisLeft();
            xAxis.setEnabled(true);
            xAxis.setCenterAxisLabels(true);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(getTopicScoreValue()));

            /*left.setDrawLabels(true);
            left.setDrawAxisLine(true);*/
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            topicScoreBarChart.getAxisLeft().setEnabled(true);
            topicScoreBarChart.getAxisRight().setEnabled(false);

            topicScoreBarChart.setDrawValueAboveBar(true);
            topicScoreBarChart.setDoubleTapToZoomEnabled(false);
            topicScoreBarChart.setPinchZoom(false);
            topicScoreBarChart.setScaleEnabled(false);
            set1.setColor(ContextCompat.getColor(getActivity(), R.color.color_incorrent_pink));
            set2.setColor(ContextCompat.getColor(getActivity(), R.color.color_corrent_blue));
            set3.setColor(ContextCompat.getColor(getActivity(), R.color.color_missed_orange));

            Legend legend = topicScoreBarChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            BarData data = new BarData(dataSets);
            data.setBarWidth(barWidth);
            topicScoreBarChart.setData(data);
            topicScoreBarChart.invalidate();
            xAxis.setAxisMaximum(i + 1);
            topicScoreBarChart.groupBars(0f, groupSpace, barSpace);
            topicScoreBarChart.animateX(2000);
        }

    }

    private ArrayList<String> getTopicScoreValue() {
        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < topicScoreRadioList.size(); i++)
            label.add(topicScoreRadioList.get(i).getTopicNm());
        return label;
    }

    void taxonomyLevel() {

        // taxonomy api call code here

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getContext());

        Call<TaxonomyLevelResponse> taxonomyLevelResponseCall = retrofitService.getTaxonomyLevel(sessionManager.getOrgId(), Const.attempt_Test_id, Const.attempt_Attempt_Count);

        taxonomyLevelResponseCall.enqueue(new Callback<TaxonomyLevelResponse>() {
            @Override
            public void onResponse(Call<TaxonomyLevelResponse> call, Response<TaxonomyLevelResponse> response) {

                if (response.isSuccessful()) {

                    TaxonomyLevelResponse taxonomyLevelResponse = response.body();

                    taxonomyLevelCountRadioList = taxonomyLevelResponse.getTaxonomyLevelCount();
                    taxonomyLevelScoreRadioList = taxonomyLevelResponse.getTaxonomyLevelScore();

                    getTaxonomyCount();
                    getTaxonomyScore();

                }

            }

            @Override
            public void onFailure(Call<TaxonomyLevelResponse> call, Throwable t) {

            }
        });

    }

    private void getTaxonomyCount() {

        // taxonomy count bar chart code here

        if (taxonomyLevelCountRadioList.size() > 0) {
            taxoCountIcon.setVisibility(View.GONE);
            taxonomyTv.setVisibility(View.VISIBLE);
            taxonomyCount.setVisibility(View.VISIBLE);
            taxonomyCountBarChart.setVisibility(View.VISIBLE);
        } else {
            taxonomyCountBarChart.setVisibility(View.GONE);
            taxoCountIcon.setVisibility(View.VISIBLE);
        }
        taxonomyCountBarChart.getDescription().setText("");
        taxonomyCountBarChart.getDescription().setTextSize(13f);

        List<BarEntry> entries = new ArrayList<>();
        List<BarEntry> entries1 = new ArrayList<>();
        List<BarEntry> entries2 = new ArrayList<>();

        for (int i = 0; i < taxonomyLevelCountRadioList.size(); i++) {

            entries.add(new BarEntry(i, Float.parseFloat(taxonomyLevelCountRadioList.get(i).getIncorrect())));
            entries1.add(new BarEntry(i, Float.parseFloat(taxonomyLevelCountRadioList.get(i).getCorrect())));
            entries2.add(new BarEntry(i, Float.parseFloat(taxonomyLevelCountRadioList.get(i).getMissed())));

            Log.e("incorrent", taxonomyLevelCountRadioList.get(i).getIncorrect());

            BarDataSet set1 = new BarDataSet(entries, "Incorrect");
            BarDataSet set2 = new BarDataSet(entries1, "Correct");
            BarDataSet set3 = new BarDataSet(entries2, "Missed");

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            dataSets.add(set2);
            dataSets.add(set3);

            float groupSpace = 0.20f;
            float barSpace = 0.09f;
            float barWidth = 0.17f;

            XAxis xAxis = taxonomyCountBarChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setDrawLabels(true);

            //xAxis.setLabelCount(4, true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            YAxis left = taxonomyCountBarChart.getAxisLeft();
            xAxis.setEnabled(true);
            xAxis.setCenterAxisLabels(true);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(getTaxonomyCountValue()));

           /* left.setDrawLabels(true);
            left.setDrawAxisLine(true);*/
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            taxonomyCountBarChart.getAxisLeft().setEnabled(true);
            taxonomyCountBarChart.getAxisRight().setEnabled(false);

            taxonomyCountBarChart.setDrawValueAboveBar(true);
            taxonomyCountBarChart.setDoubleTapToZoomEnabled(false);
            taxonomyCountBarChart.setPinchZoom(false);
            taxonomyCountBarChart.setScaleEnabled(false);
            set1.setColor(ContextCompat.getColor(getActivity(), R.color.color_incorrent_pink));
            set2.setColor(ContextCompat.getColor(getActivity(), R.color.color_corrent_blue));
            set3.setColor(ContextCompat.getColor(getActivity(), R.color.color_missed_orange));

            Legend legend = taxonomyCountBarChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            BarData data = new BarData(dataSets);
            data.setBarWidth(barWidth);
            taxonomyCountBarChart.setData(data);
            taxonomyCountBarChart.invalidate();
            xAxis.setAxisMaximum(i + 1);
            taxonomyCountBarChart.groupBars(0f, groupSpace, barSpace);
            taxonomyCountBarChart.animateX(2000);
        }
    }

    private ArrayList<String> getTaxonomyCountValue() {
        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < taxonomyLevelCountRadioList.size(); i++)
            label.add(taxonomyLevelCountRadioList.get(i).getTaxonomyNm());
        return label;

    }

    private void getTaxonomyScore() {

// taxonomy score bar chart code here

        if (taxonomyLevelScoreRadioList.size() > 0) {
            taxScoreIcon.setVisibility(View.GONE);
            taxonomyTv.setVisibility(View.VISIBLE);
            taxnomyScore.setVisibility(View.VISIBLE);
            taxnomyScoreBarChart.setVisibility(View.VISIBLE);

        } else {
            taxnomyScoreBarChart.setVisibility(View.GONE);
            taxScoreIcon.setVisibility(View.VISIBLE);
        }
        taxnomyScoreBarChart.getDescription().setText("");
        taxnomyScoreBarChart.getDescription().setTextSize(13f);

        List<BarEntry> entries = new ArrayList<>();
        List<BarEntry> entries1 = new ArrayList<>();
        List<BarEntry> entries2 = new ArrayList<>();

        for (int i = 0; i < taxonomyLevelScoreRadioList.size(); i++) {

            entries.add(new BarEntry(i, Float.parseFloat(taxonomyLevelScoreRadioList.get(i).getIncorrect())));
            entries1.add(new BarEntry(i, Float.parseFloat(taxonomyLevelScoreRadioList.get(i).getCorrect())));
            entries2.add(new BarEntry(i, Float.parseFloat(taxonomyLevelScoreRadioList.get(i).getMissed())));

            Log.e("incorrent", taxonomyLevelScoreRadioList.get(i).getIncorrect());

            BarDataSet set1 = new BarDataSet(entries, "Incorrect");
            BarDataSet set2 = new BarDataSet(entries1, "Correct");
            BarDataSet set3 = new BarDataSet(entries2, "Missed");

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            dataSets.add(set2);
            dataSets.add(set3);

            float groupSpace = 0.20f;
            float barSpace = 0.09f;
            float barWidth = 0.17f;

            XAxis xAxis = taxnomyScoreBarChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setDrawLabels(true);

            //xAxis.setLabelCount(4, true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            YAxis left = taxnomyScoreBarChart.getAxisLeft();
            xAxis.setEnabled(true);
            xAxis.setCenterAxisLabels(true);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(getTaxonomyScoreValue()));

           /* left.setDrawLabels(true);
            left.setDrawAxisLine(true);*/
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            taxnomyScoreBarChart.getAxisLeft().setEnabled(true);
            taxnomyScoreBarChart.getAxisRight().setEnabled(false);

            taxnomyScoreBarChart.setDrawValueAboveBar(true);
            taxnomyScoreBarChart.setDoubleTapToZoomEnabled(false);
            taxnomyScoreBarChart.setPinchZoom(false);
            taxnomyScoreBarChart.setScaleEnabled(false);
            set1.setColor(ContextCompat.getColor(getActivity(), R.color.color_incorrent_pink));
            set2.setColor(ContextCompat.getColor(getActivity(), R.color.color_corrent_blue));
            set3.setColor(ContextCompat.getColor(getActivity(), R.color.color_missed_orange));

            Legend legend = taxnomyScoreBarChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            BarData data = new BarData(dataSets);
            data.setBarWidth(barWidth);
            taxnomyScoreBarChart.setData(data);
            taxnomyScoreBarChart.invalidate();
            xAxis.setAxisMaximum(i + 1);
            taxnomyScoreBarChart.groupBars(0f, groupSpace, barSpace);
            taxnomyScoreBarChart.animateX(2000);
        }
    }

    private ArrayList<String> getTaxonomyScoreValue() {
        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < taxonomyLevelScoreRadioList.size(); i++)
            label.add(taxonomyLevelScoreRadioList.get(i).getTaxonomyNm());
        return label;
    }

    void difficultyLevel() {

        // difficulty level api call code here

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getContext());

        Call<DifficultyLevelResponse> difficultyLevelResponseCall = retrofitService.getDifficultyLevel(sessionManager.getOrgId(), Const.attempt_Test_id, Const.attempt_Attempt_Count);

        difficultyLevelResponseCall.enqueue(new Callback<DifficultyLevelResponse>() {
            @Override
            public void onResponse(Call<DifficultyLevelResponse> call, Response<DifficultyLevelResponse> response) {

                if (response.isSuccessful()) {

                    DifficultyLevelResponse difficultyLevelResponse = response.body();

                    difficultyLevelCountRadioList = difficultyLevelResponse.getDifficultyLevelCount();

                    difficultyLevelScoreRadioList = difficultyLevelResponse.getDifficultyLevelScore();

                        noDataInsightsIcon.setVisibility(View.GONE);
                        noDataTv.setVisibility(View.GONE);

                        getDifficultyCount();
                        getDifficultyScore();

                        progressBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<DifficultyLevelResponse> call, Throwable t) {

            }
        });
    }

    private void getDifficultyCount() {

// difficulty level score bar chart code here

        if (difficultyLevelCountRadioList.size() > 0) {
            insufficientDataCount.setVisibility(View.GONE);
            difficultyTv.setVisibility(View.VISIBLE);
            difficultyCount.setVisibility(View.VISIBLE);
            difficultyCountBarChart.setVisibility(View.VISIBLE);
        } else {
            insufficientDataCount.setVisibility(View.VISIBLE);
            difficultyCountBarChart.setVisibility(View.GONE);
        }

        difficultyCountBarChart.getDescription().setText("");
        difficultyCountBarChart.getDescription().setTextSize(13f);

        List<BarEntry> entries = new ArrayList<>();
        List<BarEntry> entries1 = new ArrayList<>();
        List<BarEntry> entries2 = new ArrayList<>();

        for (int i = 0; i < difficultyLevelCountRadioList.size(); i++) {

            entries.add(new BarEntry(i, Float.parseFloat(difficultyLevelCountRadioList.get(i).getIncorrect())));
            entries1.add(new BarEntry(i, Float.parseFloat(difficultyLevelCountRadioList.get(i).getCorrect())));
            entries2.add(new BarEntry(i, Float.parseFloat(difficultyLevelCountRadioList.get(i).getMissed())));

            Log.e("incorrent", difficultyLevelCountRadioList.get(i).getIncorrect());

            BarDataSet set1 = new BarDataSet(entries, "Incorrect");
            BarDataSet set2 = new BarDataSet(entries1, "Correct");
            BarDataSet set3 = new BarDataSet(entries2, "Missed");

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            dataSets.add(set2);
            dataSets.add(set3);

            float groupSpace = 0.20f;
            float barSpace = 0.09f;
            float barWidth = 0.17f;

            XAxis xAxis = difficultyCountBarChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setDrawLabels(true);

            //xAxis.setLabelCount(4, true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            YAxis left = difficultyCountBarChart.getAxisLeft();
            xAxis.setEnabled(true);
            xAxis.setCenterAxisLabels(true);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(getdifficultyCountValue()));

           /* left.setDrawLabels(true);
            left.setDrawAxisLine(true);*/
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            difficultyCountBarChart.getAxisLeft().setEnabled(true);
            difficultyCountBarChart.getAxisRight().setEnabled(false);

            difficultyCountBarChart.setDrawValueAboveBar(true);
            difficultyCountBarChart.setDoubleTapToZoomEnabled(false);
            difficultyCountBarChart.setPinchZoom(false);
            difficultyCountBarChart.setScaleEnabled(false);
            set1.setColor(ContextCompat.getColor(getActivity(), R.color.color_incorrent_pink));
            set2.setColor(ContextCompat.getColor(getActivity(), R.color.color_corrent_blue));
            set3.setColor(ContextCompat.getColor(getActivity(), R.color.color_missed_orange));

            Legend legend = difficultyCountBarChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            BarData data = new BarData(dataSets);
            data.setBarWidth(barWidth);
            difficultyCountBarChart.setData(data);
            difficultyCountBarChart.invalidate();
            xAxis.setAxisMaximum(i + 1);
            difficultyCountBarChart.groupBars(0f, groupSpace, barSpace);
            difficultyCountBarChart.animateX(2000);

        }
    }

    private ArrayList<String> getdifficultyCountValue() {

        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < difficultyLevelCountRadioList.size(); i++)
            label.add(difficultyLevelCountRadioList.get(i).getDiffLvlNm());
        return label;
    }

    private void getDifficultyScore() {

        // difficulty level score bar chart code here

        if (difficultyLevelScoreRadioList.size() > 0) {
            insufficientDataScore.setVisibility(View.GONE);
            difficultyTv.setVisibility(View.VISIBLE);
            difficultyScore.setVisibility(View.VISIBLE);
            difficultyScoreBarChart.setVisibility(View.VISIBLE);
        } else {
            difficultyScoreBarChart.setVisibility(View.GONE);
            insufficientDataScore.setVisibility(View.VISIBLE);
        }

        difficultyScoreBarChart.getDescription().setText("");
        difficultyScoreBarChart.getDescription().setTextSize(13f);

        List<BarEntry> entries = new ArrayList<>();
        List<BarEntry> entries1 = new ArrayList<>();
        List<BarEntry> entries2 = new ArrayList<>();

        for (int i = 0; i < difficultyLevelScoreRadioList.size(); i++) {

            entries.add(new BarEntry(i, Float.parseFloat(difficultyLevelScoreRadioList.get(i).getIncorrect())));
            entries1.add(new BarEntry(i, Float.parseFloat(difficultyLevelScoreRadioList.get(i).getCorrect())));
            entries2.add(new BarEntry(i, Float.parseFloat(difficultyLevelScoreRadioList.get(i).getMissed())));

            Log.e("incorrent", difficultyLevelScoreRadioList.get(i).getIncorrect());

            BarDataSet set1 = new BarDataSet(entries, "Incorrect");
            BarDataSet set2 = new BarDataSet(entries1, "Correct");
            BarDataSet set3 = new BarDataSet(entries2, "Missed");

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            dataSets.add(set2);
            dataSets.add(set3);

            float groupSpace = 0.20f;
            float barSpace = 0.09f;
            float barWidth = 0.17f;

            XAxis xAxis = difficultyScoreBarChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setDrawLabels(true);

            //xAxis.setLabelCount(4, true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            YAxis left = difficultyScoreBarChart.getAxisLeft();
            xAxis.setEnabled(true);
            xAxis.setCenterAxisLabels(true);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(getdifficultyScoreValue()));

           /* left.setDrawLabels(true);
            left.setDrawAxisLine(true);*/
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            difficultyScoreBarChart.getAxisLeft().setEnabled(true);
            difficultyScoreBarChart.getAxisRight().setEnabled(false);

            difficultyScoreBarChart.setDrawValueAboveBar(true);
            difficultyScoreBarChart.setDoubleTapToZoomEnabled(false);
            difficultyScoreBarChart.setPinchZoom(false);
            difficultyScoreBarChart.setScaleEnabled(false);
            set1.setColor(ContextCompat.getColor(getActivity(), R.color.color_incorrent_pink));
            set2.setColor(ContextCompat.getColor(getActivity(), R.color.color_corrent_blue));
            set3.setColor(ContextCompat.getColor(getActivity(), R.color.color_missed_orange));

            Legend legend = difficultyScoreBarChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            BarData data = new BarData(dataSets);
            data.setBarWidth(barWidth);
            difficultyScoreBarChart.setData(data);
            difficultyScoreBarChart.invalidate();
            xAxis.setAxisMaximum(i + 1);
            difficultyScoreBarChart.groupBars(0f, groupSpace, barSpace);
            difficultyScoreBarChart.animateX(2000);

        }
    }

    private ArrayList<String> getdifficultyScoreValue() {
        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < difficultyLevelScoreRadioList.size(); i++)
            label.add(difficultyLevelScoreRadioList.get(i).getDiffLvlNm());
        return label;
    }

    void getTestName() {

        // spinner test name code here

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getContext());

        Call<List<TestNameResponse>> listCall = retrofitService.getTestnameRes(sessionManager.getOrgId());

        listCall.enqueue(new Callback<List<TestNameResponse>>() {
            @Override
            public void onResponse(Call<List<TestNameResponse>> call, Response<List<TestNameResponse>> response) {

                if (response.isSuccessful()) {

                    final List<TestNameResponse> testNameResponse = response.body();

                    Log.e("list", String.valueOf(testNameResponse.size()));

                        List<String> list = new ArrayList<>();

                        for (int i = 0; i < testNameResponse.size(); i++) {

                            list.add(testNameResponse.get(i).testName);

                        }

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.simple_list_item, list);

                        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        testNameSpinner.setAdapter(arrayAdapter);


                        testNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                                int currentItem = testNameSpinner.getSelectedItemPosition();
                                String currentItemCount = testNameSpinner.getSelectedItem().toString();

                                if (!currentItemCount.isEmpty()) {

                                    Const.attempt_Test_id = testNameResponse.get(position).testId;
                                    progressBar.setVisibility(View.VISIBLE);
                                    getAttempt();
                                    progressBar.setVisibility(View.GONE);
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    }else {

                    progressBar.setVisibility(View.GONE);

                    Log.e("error code", String.valueOf(response.code()));
                    Log.e("error msg", String.valueOf(response.message()));

                    if(response.code() == 404 ){

                        // binding.pageNotFoundIconInsights.setVisibility(View.VISIBLE);

                        new AlertDialog.Builder(getContext()).
                                setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                })
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
                                .setMessage("401, Unauthorized error").create().show();

                    }else  if(response.code() == 400) {

                       // binding.badRequestIconDashboardInsights.setVisibility(View.VISIBLE);

                        new AlertDialog.Builder(getContext()).
                                setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setMessage("400, Bad Request Error").create().show();

                    }else {

                        //binding.serverErrorIconDashboardInsights.setVisibility(View.VISIBLE);
                        new AlertDialog.Builder(getContext()).
                                setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setMessage("500, Server takes too long to respond").create().show();
                    }
                }
                }


            @Override
            public void onFailure(Call<List<TestNameResponse>> call, Throwable t) {


            }
        });

    }

    void getAttempt() {

        // spinner test attempts code here

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getContext());

        Call<TestAttemptResponse> testAttemptResponseCall = retrofitService.getTestAttemptRes(sessionManager.getOrgId(), Const.attempt_Test_id);

        Log.e("org", String.valueOf(sessionManager.getOrgId()));

        Log.e("test", String.valueOf(sessionManager.getTestId()));

        testAttemptResponseCall.enqueue(new Callback<TestAttemptResponse>() {
            @Override
            public void onResponse(Call<TestAttemptResponse> call, Response<TestAttemptResponse> response) {

                if (response.isSuccessful()) {

                    final TestAttemptResponse testAttemptResponse = response.body();

                    testAttemptList = testAttemptResponse.getAttempts();

                        List<String> list = new ArrayList<>();

                        for (int i = 0; i < testAttemptList.size(); i++) {

                            list.add(String.valueOf(testAttemptList.get(i).getCurrentAttempt()));

                            Log.e(TAG, String.valueOf(testAttemptList.get(i).currentAttempt));
                        }

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.simple_list_item, list);

                        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        attemptSpinner.setAdapter(arrayAdapter);

                        final int currentItem = attemptSpinner.getSelectedItemPosition();

                        attemptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                String currentItemCount = testNameSpinner.getSelectedItem().toString();

                                if (!currentItemCount.isEmpty()) {

                                    Const.attempt_Test_id = Integer.parseInt((testAttemptResponse.testId));
                                    Const.attempt_Attempt_Count = (testAttemptResponse.getAttempts().get(position).currentAttempt);

                                    progressBar.setVisibility(View.VISIBLE);

                                    difficultyLevel();
                                    taxonomyLevel();
                                    topicLevel();
                                    strongWeakArea();
                                    testScoreBoard();
                                    progressBar.setVisibility(View.GONE);
                               /* if (difficultyLevelCountRadioList.size() == 0 && difficultyLevelScoreRadioList.size() == 0
                                        &&taxonomyLevelCountRadioList.size() == 0 && taxonomyLevelScoreRadioList.size() == 0
                                        && topicCountRadioList.size() == 0 && topicScoreRadioList.size() == 0
                                        && strongAreaRadioList.size() == 0&& weakAreaRadioList.size() == 0 ){

                                    binding.noDataInsghts.setVisibility(View.VISIBLE);

                                }         */
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                }


            @Override
            public void onFailure(Call<TestAttemptResponse> call, Throwable t) {

            }
        });

    }

}