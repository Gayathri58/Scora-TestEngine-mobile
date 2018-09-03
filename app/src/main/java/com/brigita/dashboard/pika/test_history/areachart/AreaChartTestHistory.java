package com.brigita.dashboard.pika.test_history.areachart;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.brigita.dashboard.pika.Const.Const;
import com.brigita.dashboard.pika.R;
import com.brigita.dashboard.pika.SessionManager;
import com.brigita.dashboard.pika.databinding.ActivityChartTestHistoryBinding;
import com.brigita.dashboard.pika.retrofit.RetrofitService;
import com.brigita.dashboard.pika.retrofit.RetrofitServiceGenerator;
import com.brigita.dashboard.pika.test_history.areachart.adapter.ScoreBoardTableAdapter;
import com.brigita.dashboard.pika.test_history.areachart.model.AreaChartResponse;
import com.brigita.dashboard.pika.test_history.areachart.model.DifficultyLevel;
import com.brigita.dashboard.pika.test_history.areachart.model.DifficultyLevelCount;
import com.brigita.dashboard.pika.test_history.areachart.model.DifficultyLevelScore;
import com.brigita.dashboard.pika.test_history.areachart.model.StrongArea;
import com.brigita.dashboard.pika.test_history.areachart.model.StrongWeakArea;
import com.brigita.dashboard.pika.test_history.areachart.model.TaxonomyLevel;
import com.brigita.dashboard.pika.test_history.areachart.model.TaxonomyLevelCount;
import com.brigita.dashboard.pika.test_history.areachart.model.TaxonomyLevelScore;
import com.brigita.dashboard.pika.test_history.areachart.model.TestScoreBoardInstance;
import com.brigita.dashboard.pika.test_history.areachart.model.TopicCount;
import com.brigita.dashboard.pika.test_history.areachart.model.TopicLevel;
import com.brigita.dashboard.pika.test_history.areachart.model.TopicScore;
import com.brigita.dashboard.pika.test_history.areachart.model.WeakArea;
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
 * Created by BRIGITA on 06-07-2018.
 */

public class AreaChartTestHistory extends AppCompatActivity {

    private SessionManager sessionManager;
    ActivityChartTestHistoryBinding binding;
    private static final String TAG = AreaChartTestHistory.class.getSimpleName();
    TextView difficultyLevelTv;
    TextView difficultyLevelCountTv;
    TextView difficultyLevelScoreTv;
    TextView taxonomyLevelTv;
    TextView taxonomyLevelCountTv;
    TextView taxonomyLevelScoreTv;
    TextView topicLevelTv;
    TextView topicLevelCountTv;
    TextView topicLevelScoreTv;
    TextView strongAreaTv;
    TextView weakAreaTv;
    TextView strongWeakAreaTv;

    List<DifficultyLevelCount> difficultyLevelCountList;
    List<DifficultyLevelScore> difficultyLevelScoreList;
    List<TaxonomyLevelCount> taxonomyLevelCountList;
    List<TaxonomyLevelScore> taxonomyLevelScoreList;
    List<TopicCount> topicCountList;
    List<TopicScore> topicScoreList;
    List<StrongArea> strongAreaList;
    List<WeakArea> weakAreaList;

    BarChart difficultyLevelContBarChar;
    BarChart difficultyLevelScoreBarChart;
    BarChart taxonomyLevelCountBarChart;
    BarChart taxonomyLevelScoreBarChart;
    BarChart topicLevelCountChart;
    BarChart topicLevelScoreChart;
    BarChart strongAreaChart;
    BarChart weakAreaChart;

    public AreaChartTestHistory() {

        // Required empty public constructor
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chart_test_history);

        difficultyLevelContBarChar = binding.testHistoryAttemptChart.findViewById(R.id.test_history_attempt_chart);
        difficultyLevelCountTv = binding.difficultyCountTv.findViewById(R.id.difficulty_count_tv);
        difficultyLevelTv = binding.testHistoryDifficultLevelTv.findViewById(R.id.test_history_difficult_level_tv);

        difficultyLevelScoreBarChart = binding.testHistoryScoreChart.findViewById(R.id.test_history_score_chart);
        difficultyLevelScoreTv = binding.difficultyScoreTv.findViewById(R.id.difficulty_score_tv);

        taxonomyLevelCountBarChart = binding.taxonomyCountChart.findViewById(R.id.taxonomy_count_chart);
        taxonomyLevelCountTv = binding.taxonomyCountTv.findViewById(R.id.taxonomy_count_tv);
        taxonomyLevelTv = binding.testHistoryTaxonomyLevelTv.findViewById(R.id.test_history_taxonomy_level_tv);

        taxonomyLevelScoreBarChart = binding.taxonomyScoreChart.findViewById(R.id.taxonomy_score_chart);
        taxonomyLevelScoreTv = binding.taxonomyScoreTv.findViewById(R.id.taxonomy_score_tv);

        topicLevelCountChart = binding.topicCountChart.findViewById(R.id.topic_count_chart);
        topicLevelCountTv = binding.topicCountTv.findViewById(R.id.topic_count_tv);
        topicLevelTv = binding.testHistoryTopicLevelTv.findViewById(R.id.test_history_topic_level_tv);

        topicLevelScoreChart = binding.topicScoreChart.findViewById(R.id.topic_score_chart);
        topicLevelScoreTv = binding.topicScoreTv.findViewById(R.id.topic_score_tv);

        strongAreaChart = binding.strongAreaChart.findViewById(R.id.strong_area_chart);
        strongAreaTv = binding.strongAreaTv.findViewById(R.id.strong_area_tv);
        strongWeakAreaTv = binding.strongWeakAreaTv.findViewById(R.id.strong_weak_area_tv);

        weakAreaChart = binding.weakAreaChart.findViewById(R.id.weak_area_chart);
        weakAreaTv = binding.weakAreaTv.findViewById(R.id.weak_area_tv);

        sessionManager = new SessionManager(getApplicationContext());

        areaChartScoreBoard();

    }

    void areaChartScoreBoard() {

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getApplicationContext());

        Call<AreaChartResponse> areaChartResponseCall = retrofitService.getAreaChart(sessionManager.getOrgId(), Const.test_id, Const.currentAttempt, Const.schedule_id); //2,13,1,47

        areaChartResponseCall.enqueue(new Callback<AreaChartResponse>() {
            @Override
            public void onResponse(Call<AreaChartResponse> call, Response<AreaChartResponse> response) {

                if (response.isSuccessful()) {

                    List<TestScoreBoardInstance> testScoreBoardInstanceList = response.body().getTestScoreBoardInstance();

                    ScoreBoardTableAdapter adapter = new ScoreBoardTableAdapter(testScoreBoardInstanceList, getApplicationContext());

                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    binding.scoreboardTableRv.setLayoutManager(manager);
                    binding.scoreboardTableRv.setAdapter(adapter);


                    binding.areaChartProgressBar.setVisibility(View.GONE);

                    DifficultyLevel difficultyLevel = response.body().getDifficultyLevel();

                    difficultyLevelCountList = difficultyLevel.getDifficultyLevelCount();
                    difficultyLevelScoreList = difficultyLevel.getDifficultyLevelScore();

                    TaxonomyLevel taxonomyLevel = response.body().getTaxonomyLevel();

                    taxonomyLevelCountList = taxonomyLevel.getTaxonomyLevelCount();
                    taxonomyLevelScoreList = taxonomyLevel.getTaxonomyLevelScore();

                    TopicLevel topicLevel = response.body().getTopicLevel();

                    topicCountList = topicLevel.getTopicCount();
                    topicScoreList = topicLevel.getTopicScore();

                    StrongWeakArea strongWeakArea = response.body().getStrongWeakArea();

                    strongAreaList = strongWeakArea.getStrongArea();
                    weakAreaList = strongWeakArea.getWeakArea();

                    difficultyLevelCount();
                    difficultyLevelscore();
                    taxonomyLevelCount();
                    taxonomyLevelScore();
                    topicLevelScore();
                    topicLevelCount();
                    strongArea();
                    weakArea();

                    Log.e(TAG, String.valueOf(response.code()));

                } else {
                    Toast.makeText(getApplicationContext(), "else", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AreaChartResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), " No Chart Available Here", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void difficultyLevelCount() {

        if (difficultyLevelCountList.size() > 0) {
            //difficultyLevelCountTv.setVisibility(View.VISIBLE);
            difficultyLevelContBarChar.setVisibility(View.VISIBLE);
        }

        difficultyLevelContBarChar.getDescription().setEnabled(false);
        //difficultyLevelContBarChar.getDescription().setTextSize(13f);

        List<BarEntry> entries = new ArrayList<>();
        List<BarEntry> entries1 = new ArrayList<>();
        List<BarEntry> entries2 = new ArrayList<>();

        for (int i = 0; i < difficultyLevelCountList.size(); i++) {

          /*  Float easy[] ;
            if(difficultyLevelCountList.get(i).diffLvlNm == "Easy"){
                difficultyLevelCountList.get(i).seqq = 1;
            }
            */
            entries.add(new BarEntry(i, Float.parseFloat(difficultyLevelCountList.get(i).getIncorrect())));
            entries1.add(new BarEntry(i, Float.parseFloat(difficultyLevelCountList.get(i).getCorrect())));
            entries2.add(new BarEntry(i, Float.parseFloat(difficultyLevelCountList.get(i).getMissed())));


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

            XAxis xAxis = difficultyLevelContBarChar.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setDrawLabels(true);
            xAxis.setCenterAxisLabels(true);
            xAxis.setDrawAxisLine(false);

            //xAxis.setLabelCount(4, true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            YAxis left = difficultyLevelContBarChar.getAxisLeft();
            xAxis.setEnabled(true);
            // final String[] difficultyCount = {"Easy", "Hard", "Medium"};
            xAxis.setValueFormatter(new IndexAxisValueFormatter(getdifficultyCountValue()));

          /*  set1.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return entry.getData().toString();
                }

            });
            set2.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return entry.getData().toString();
                }

            });
            set3.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return entry.getData().toString();
                }

            });*/
            /*  left.setDrawLabels(true);*/
            left.setDrawAxisLine(true);
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            difficultyLevelContBarChar.getAxisRight().setEnabled(false);
            difficultyLevelContBarChar.setDrawGridBackground(false);

            difficultyLevelContBarChar.setDrawValueAboveBar(true);
            difficultyLevelContBarChar.setDoubleTapToZoomEnabled(false);
            difficultyLevelContBarChar.setPinchZoom(false);
            difficultyLevelContBarChar.setScaleEnabled(false);
            set1.setColor(ContextCompat.getColor(this, R.color.color_incorrent_pink));
            set2.setColor(ContextCompat.getColor(this, R.color.color_corrent_blue));
            set3.setColor(ContextCompat.getColor(this, R.color.color_missed_orange));

            Legend legend = difficultyLevelContBarChar.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            BarData data = new BarData(dataSets);
            data.setBarWidth(barWidth);
            difficultyLevelContBarChar.setData(data);
            xAxis.setAxisMaximum(i + 1);
            difficultyLevelContBarChar.groupBars(0f, groupSpace, barSpace);
            difficultyLevelContBarChar.animateX(1000);
            difficultyLevelContBarChar.invalidate();
        }
    }

    private void difficultyLevelscore() {


        if (difficultyLevelScoreList.size() > 0) {
            //difficultyLevelScoreTv.setVisibility(View.VISIBLE);
            difficultyLevelScoreBarChart.setVisibility(View.VISIBLE);
        }

        difficultyLevelScoreBarChart.getDescription().setText("");
        difficultyLevelScoreBarChart.getDescription().setTextSize(13f);

        List<BarEntry> entries = new ArrayList<>();
        List<BarEntry> entries1 = new ArrayList<>();
        List<BarEntry> entries2 = new ArrayList<>();

        for (int i = 0; i < difficultyLevelScoreList.size(); i++) {
            entries.add(new BarEntry(i, Float.parseFloat(difficultyLevelScoreList.get(i).getIncorrect())));
            entries1.add(new BarEntry(i, Float.parseFloat(difficultyLevelScoreList.get(i).getCorrect())));
            entries2.add(new BarEntry(i, Float.parseFloat(difficultyLevelScoreList.get(i).getMissed())));

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

            XAxis xAxis = difficultyLevelScoreBarChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setDrawLabels(true);

            //xAxis.setLabelCount(3, true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            // final String[] values = new String[]{"Easy", "Hard", "Medium"};
            YAxis left = difficultyLevelScoreBarChart.getAxisLeft();
            xAxis.setEnabled(true);
            xAxis.setCenterAxisLabels(true);
            xAxis.setDrawAxisLine(false);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(getdifficultyScoreValues()));

            /*set1.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return entry.getData().toString();
                }

            });
            set2.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return entry.getData().toString();
                }

            });
            set3.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return entry.getData().toString();
                }

            });*/
           /* left.setDrawLabels(true);
            left.setDrawAxisLine(true);*/
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            difficultyLevelScoreBarChart.getAxisLeft().setEnabled(true);
            difficultyLevelScoreBarChart.getAxisRight().setEnabled(false);

            difficultyLevelScoreBarChart.setDrawValueAboveBar(true);
            difficultyLevelScoreBarChart.setDoubleTapToZoomEnabled(false);
            difficultyLevelScoreBarChart.setPinchZoom(false);
            difficultyLevelScoreBarChart.setScaleEnabled(false);
            set1.setColor(ContextCompat.getColor(this, R.color.color_incorrent_pink));
            set2.setColor(ContextCompat.getColor(this, R.color.color_corrent_blue));
            set3.setColor(ContextCompat.getColor(this, R.color.color_missed_orange));

            Legend legend = difficultyLevelScoreBarChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            BarData data = new BarData(dataSets);
            data.setBarWidth(barWidth);
            difficultyLevelScoreBarChart.setData(data);
            difficultyLevelScoreBarChart.invalidate();
            xAxis.setAxisMaximum(i + 1);
            difficultyLevelScoreBarChart.groupBars(0f, groupSpace, barSpace);
            difficultyLevelScoreBarChart.animateX(1000);

        }
    }

    private void taxonomyLevelCount() {

        if (taxonomyLevelCountList.size() > 0) {
            taxonomyLevelCountBarChart.setVisibility(View.VISIBLE);
        }

        taxonomyLevelCountBarChart.getDescription().setEnabled(false);
        //taxonomyLevelCountBarChart.getDescription().setTextSize(13f);

        List<BarEntry> entries = new ArrayList<>();
        List<BarEntry> entries1 = new ArrayList<>();
        List<BarEntry> entries2 = new ArrayList<>();

        for (int i = 0; i < taxonomyLevelCountList.size(); i++) {
            entries.add(new BarEntry(i, Float.parseFloat(taxonomyLevelCountList.get(i).getIncorrect())));
            entries1.add(new BarEntry(i, Float.parseFloat(taxonomyLevelCountList.get(i).getCorrect())));
            entries2.add(new BarEntry(i, Float.parseFloat(taxonomyLevelCountList.get(i).getMissed())));

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

            XAxis xAxis = taxonomyLevelCountBarChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setDrawLabels(true);
            xAxis.setCenterAxisLabels(true);
            xAxis.setDrawAxisLine(false);
            //xAxis.setLabelCount(8, true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            YAxis left = taxonomyLevelCountBarChart.getAxisLeft();
            xAxis.setEnabled(true);
            //final String[] taxonomyCount = {"Analyze", "Apply", "Create", "Evaluate", "Understand"};
            xAxis.setValueFormatter(new IndexAxisValueFormatter(gettaxonomyCountValue()));
                    /*set1.setValueFormatter(new IValueFormatter() {

                        @Override
                        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                            return entry.getData().toString();
                        }

                    });
                    set2.setValueFormatter(new IValueFormatter() {

                        @Override
                        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                            return entry.getData().toString();
                        }

                    });
                    set3.setValueFormatter(new IValueFormatter() {

                        @Override
                        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                            return entry.getData().toString();
                        }

                    });*/
         /*   left.setDrawLabels(true);
            left.setDrawAxisLine(true);*/
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            taxonomyLevelCountBarChart.getAxisLeft().setEnabled(true);
            taxonomyLevelCountBarChart.getAxisRight().setEnabled(false);

            taxonomyLevelCountBarChart.setDrawValueAboveBar(true);
            taxonomyLevelCountBarChart.setDoubleTapToZoomEnabled(false);
            taxonomyLevelCountBarChart.setPinchZoom(false);
            taxonomyLevelCountBarChart.setScaleEnabled(false);
            set1.setColor(ContextCompat.getColor(this, R.color.color_incorrent_pink));
            set2.setColor(ContextCompat.getColor(this, R.color.color_corrent_blue));
            set3.setColor(ContextCompat.getColor(this, R.color.color_missed_orange));

            Legend legend = taxonomyLevelCountBarChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            BarData data = new BarData(dataSets);
            data.setBarWidth(barWidth);
            xAxis.setAxisMaximum(i + 1);
            taxonomyLevelCountBarChart.setData(data);
            taxonomyLevelCountBarChart.invalidate();
            taxonomyLevelCountBarChart.groupBars(0f, groupSpace, barSpace);
            taxonomyLevelCountBarChart.animateX(1000);

        }
    }

    private void taxonomyLevelScore() {

        if (taxonomyLevelScoreList.size() > 0) {
            taxonomyLevelScoreBarChart.setVisibility(View.VISIBLE);
        }

        taxonomyLevelScoreBarChart.getDescription().setEnabled(false);
        //taxonomyLevelScoreBarChart.getDescription().setTextSize(13f);

        List<BarEntry> entries = new ArrayList<>();
        List<BarEntry> entries1 = new ArrayList<>();
        List<BarEntry> entries2 = new ArrayList<>();

        for (int i = 0; i < taxonomyLevelScoreList.size(); i++) {
            entries.add(new BarEntry(i, Float.parseFloat(taxonomyLevelScoreList.get(i).getIncorrect())));
            entries1.add(new BarEntry(i, Float.parseFloat(taxonomyLevelScoreList.get(i).getCorrect())));
            entries2.add(new BarEntry(i, Float.parseFloat(taxonomyLevelScoreList.get(i).getMissed())));

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

            XAxis xAxis = taxonomyLevelScoreBarChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setDrawLabels(true);
            xAxis.setCenterAxisLabels(true);
            xAxis.setDrawAxisLine(false);
            //xAxis.setLabelCount(3, true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            YAxis left = taxonomyLevelScoreBarChart.getAxisLeft();
            //left.setAxisMinimum(-10f);
            xAxis.setEnabled(true);
            //final String[] taxonomyScore = {"Analyze", "Apply", "Create", "Evaluate", "Understand"};
            xAxis.setValueFormatter(new IndexAxisValueFormatter(gettaxonomyScoreValue()));
            /*set1.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return entry.getData().toString();
                }

            });
            set2.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return entry.getData().toString();
                }

            });
            set3.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return entry.getData().toString();
                }

            });*/
           /* left.setDrawLabels(true);
            left.setDrawAxisLine(true);*/
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            taxonomyLevelScoreBarChart.getAxisLeft().setEnabled(true);
            taxonomyLevelScoreBarChart.getAxisRight().setEnabled(false);

            taxonomyLevelScoreBarChart.setDrawValueAboveBar(true);
            taxonomyLevelScoreBarChart.setDoubleTapToZoomEnabled(false);
            taxonomyLevelScoreBarChart.setPinchZoom(false);
            taxonomyLevelScoreBarChart.setScaleEnabled(false);
            set1.setColor(ContextCompat.getColor(this, R.color.color_incorrent_pink));
            set2.setColor(ContextCompat.getColor(this, R.color.color_corrent_blue));
            set3.setColor(ContextCompat.getColor(this, R.color.color_missed_orange));

            Legend legend = taxonomyLevelScoreBarChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            BarData data = new BarData(dataSets);
            data.setBarWidth(barWidth);
            taxonomyLevelScoreBarChart.setData(data);
            taxonomyLevelScoreBarChart.invalidate();
            xAxis.setAxisMaximum(i + 1);
            taxonomyLevelScoreBarChart.groupBars(0f, groupSpace, barSpace);
            taxonomyLevelScoreBarChart.animateX(1000);

        }
    }

    private void topicLevelCount() {

        if (topicCountList.size() > 0) {
            topicLevelCountChart.setVisibility(View.VISIBLE);
        }
        topicLevelCountChart.getDescription().setText("");
        topicLevelCountChart.getDescription().setTextSize(13f);

        List<BarEntry> entries = new ArrayList<>();
        List<BarEntry> entries1 = new ArrayList<>();
        List<BarEntry> entries2 = new ArrayList<>();

        for (int i = 0; i < topicCountList.size(); i++) {
            entries.add(new BarEntry(i, Float.parseFloat(topicCountList.get(i).getIncorrect())));
            entries1.add(new BarEntry(i, Float.parseFloat(topicCountList.get(i).getCorrect())));
            entries2.add(new BarEntry(i, Float.parseFloat(topicCountList.get(i).getMissed())));

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

            XAxis xAxis = topicLevelCountChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setDrawLabels(true);
            xAxis.setDrawAxisLine(false);
            // final String[] topicCount = {"Object-Oriented Data Modeling", "Database", "Physical Database Design"};

            //xAxis.setLabelCount(4, true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            YAxis left = topicLevelCountChart.getAxisLeft();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(getXAxisValuesCount()));
            xAxis.setEnabled(true);
            xAxis.setCenterAxisLabels(true);
            /*set1.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return entry.getData().toString();
                }

            });
            set2.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return entry.getData().toString();
                }

            });
            set3.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return entry.getData().toString();
                }

            });*/
           /* left.setDrawLabels(true);
            left.setDrawAxisLine(true);*/
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            topicLevelCountChart.getAxisLeft().setEnabled(true);
            topicLevelCountChart.getAxisRight().setEnabled(false);

            topicLevelCountChart.setDrawValueAboveBar(true);
            topicLevelCountChart.setDoubleTapToZoomEnabled(false);
            topicLevelCountChart.setPinchZoom(false);
            topicLevelCountChart.setScaleEnabled(false);
            set1.setColor(ContextCompat.getColor(this, R.color.color_incorrent_pink));
            set2.setColor(ContextCompat.getColor(this, R.color.color_corrent_blue));
            set3.setColor(ContextCompat.getColor(this, R.color.color_missed_orange));

            Legend legend = topicLevelCountChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            BarData data = new BarData(dataSets);
            data.setBarWidth(barWidth);
            topicLevelCountChart.setData(data);
            topicLevelCountChart.invalidate();
            xAxis.setAxisMaximum(i + 1);
            topicLevelCountChart.groupBars(0f, groupSpace, barSpace);
            topicLevelCountChart.animateX(1000);

        }
    }

    private void topicLevelScore() {

        if (topicScoreList.size() > 0) {
            topicLevelScoreChart.setVisibility(View.VISIBLE);
        }
        topicLevelScoreChart.getDescription().setText("");
        topicLevelScoreChart.getDescription().setTextSize(13f);

        List<BarEntry> entries = new ArrayList<>();
        List<BarEntry> entries1 = new ArrayList<>();
        List<BarEntry> entries2 = new ArrayList<>();

        for (int i = 0; i < topicScoreList.size(); i++) {

            entries.add(new BarEntry(i, Float.parseFloat(topicScoreList.get(i).getIncorrect())));
            entries1.add(new BarEntry(i, Float.parseFloat(topicScoreList.get(i).getCorrect())));
            entries2.add(new BarEntry(i, Float.parseFloat(topicScoreList.get(i).getMissed())));

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

            XAxis xAxis = topicLevelScoreChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setDrawLabels(true);
            xAxis.setCenterAxisLabels(true);
            xAxis.setDrawAxisLine(false);

            //final String[] topicScore = {topicScoreList.get(j).getTopicNm()};

            //{"Object-Oriented Data Modeling", "Database", "Physical Database Design"};
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(getXAxisValues()));
            xAxis.setEnabled(true);

            //xAxis.setLabelCount(4, true);
            /*set1.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return entry.getData().toString();
                }

            });
            set2.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return entry.getData().toString();
                }

            });
            set3.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return entry.getData().toString();
                }

            });*/
            YAxis left = topicLevelScoreChart.getAxisLeft();
            /*left.setDrawLabels(true);
            left.setDrawAxisLine(true);*/
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            topicLevelScoreChart.getAxisLeft().setEnabled(true);
            topicLevelScoreChart.getAxisRight().setEnabled(false);

            topicLevelScoreChart.setDrawValueAboveBar(true);
            topicLevelScoreChart.setDoubleTapToZoomEnabled(false);
            topicLevelScoreChart.setPinchZoom(false);
            topicLevelScoreChart.setScaleEnabled(false);
            set1.setColor(ContextCompat.getColor(this, R.color.color_incorrent_pink));
            set2.setColor(ContextCompat.getColor(this, R.color.color_corrent_blue));
            set3.setColor(ContextCompat.getColor(this, R.color.color_missed_orange));

            Legend legend = topicLevelScoreChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            BarData data = new BarData(dataSets);
            data.setBarWidth(barWidth);
            topicLevelScoreChart.setData(data);
            topicLevelScoreChart.invalidate();
            xAxis.setAxisMaximum(i + 1);
            topicLevelScoreChart.groupBars(0f, groupSpace, barSpace);
            topicLevelScoreChart.animateX(1000);

        }
    }

    private void strongArea() {

        if (strongAreaList.size() > 0) {
            strongAreaChart.setVisibility(View.VISIBLE);
        }
        strongAreaChart.getDescription().setText(" ");
        strongAreaChart.getDescription().setTextSize(13f);

        List<BarEntry> entries1 = new ArrayList<>();

        for (int i = 0; i < strongAreaList.size(); i++) {
            entries1.add(new BarEntry(i, Float.parseFloat(strongAreaList.get(i).getCorrect())));

            BarDataSet set2 = new BarDataSet(entries1, "Correct");

            XAxis xAxis = strongAreaChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setDrawLabels(true);
            xAxis.setCenterAxisLabels(false);
            xAxis.setDrawAxisLine(false);
            //final String[] strongArea = {"Physical Database Design", "Object-Oriented Database"};

            //xAxis.setLabelCount(4, true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            YAxis left = strongAreaChart.getAxisLeft();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(getstrongAreaCount()));
            xAxis.setEnabled(true);

           /* left.setDrawLabels(true);
            left.setDrawAxisLine(true);*/
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            strongAreaChart.getAxisLeft().setEnabled(true);
            strongAreaChart.getAxisRight().setEnabled(false);

            strongAreaChart.setDrawValueAboveBar(true);
            strongAreaChart.setDoubleTapToZoomEnabled(false);
            strongAreaChart.setPinchZoom(false);
            strongAreaChart.setScaleEnabled(false);
            set2.setColor(ContextCompat.getColor(this, R.color.color_corrent_blue));

            Legend legend = strongAreaChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            BarData data = new BarData(set2);
            strongAreaChart.setData(data);
            strongAreaChart.invalidate();
            strongAreaChart.animateX(1000);
        }
    }

    private void weakArea() {
        if (weakAreaList.size() > 0) {
            weakAreaChart.setVisibility(View.VISIBLE);
        }
        weakAreaChart.getDescription().setText("");
        weakAreaChart.getDescription().setTextSize(13f);

        List<BarEntry> entries1 = new ArrayList<>();

        for (int i = 0; i < weakAreaList.size(); i++) {

            entries1.add(new BarEntry(i, Float.parseFloat(weakAreaList.get(i).getIncorrect())));

            BarDataSet set2 = new BarDataSet(entries1, "Incorrect");

            XAxis xAxis = weakAreaChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setDrawLabels(true);
            xAxis.setCenterAxisLabels(false);

            //final String[] weakArea = {"Physical Database Design", "Object-Oriented Database"};

            //xAxis.setLabelCount(4, true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            YAxis left = weakAreaChart.getAxisLeft();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(getweakAreaCount()));
            xAxis.setEnabled(true);
            xAxis.setDrawAxisLine(false);
           /* left.setDrawLabels(true);
            left.setDrawAxisLine(true);*/
            left.setDrawGridLines(true);
            left.setDrawZeroLine(true);
            weakAreaChart.getAxisLeft().setEnabled(true);
            weakAreaChart.getAxisRight().setEnabled(false);

            weakAreaChart.setDrawValueAboveBar(true);
            weakAreaChart.setDoubleTapToZoomEnabled(false);
            weakAreaChart.setPinchZoom(false);
            weakAreaChart.setScaleEnabled(false);
            set2.setColor(ContextCompat.getColor(this, R.color.color_incorrent_pink));

            Legend legend = weakAreaChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            BarData data = new BarData(set2);
            weakAreaChart.setData(data);
            weakAreaChart.invalidate();
            weakAreaChart.animateX(1000);
        }

    }

    public ArrayList<String> getdifficultyCountValue() {

        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < difficultyLevelCountList.size(); i++)
            label.add(difficultyLevelCountList.get(i).getDiffLvlNm());
        return label;
    }

    public ArrayList<String> getdifficultyScoreValues() {
        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < difficultyLevelScoreList.size(); i++)
            label.add(difficultyLevelScoreList.get(i).getDiffLvlNm());
        return label;
    }

    public ArrayList<String> gettaxonomyCountValue() {
        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < taxonomyLevelCountList.size(); i++)
            if (taxonomyLevelCountList.get(i).getTaxonomyNm().length() > 11) {
                label.add(taxonomyLevelCountList.get(i).getTaxonomyNm().substring(0, 10).concat("..."));
            } else {
                label.add(taxonomyLevelCountList.get(i).getTaxonomyNm());
            }
        return label;
    }

    public ArrayList<String> gettaxonomyScoreValue() {
        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < taxonomyLevelScoreList.size(); i++)
            if (taxonomyLevelScoreList.get(i).getTaxonomyNm().length() > 11) {
                label.add(taxonomyLevelScoreList.get(i).getTaxonomyNm().substring(0, 10).concat("..."));
            } else {
                label.add(taxonomyLevelScoreList.get(i).getTaxonomyNm());
            }
        return label;
    }

    public ArrayList<String> getXAxisValuesCount() {

        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < topicCountList.size(); i++)
            if (topicCountList.get(i).getTopicNm().length() > 18) {
                label.add(topicCountList.get(i).getTopicNm().substring(0, 17).concat("..."));
            } else {
                label.add(topicCountList.get(i).getTopicNm());
            }
        return label;
    }

    public ArrayList<String> getXAxisValues() {

        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < topicScoreList.size(); i++)
            if (topicScoreList.get(i).getTopicNm().length() > 18) {
                label.add(topicScoreList.get(i).getTopicNm().substring(0, 17).concat("..."));
            } else {
                label.add(topicScoreList.get(i).getTopicNm());
            }
        return label;
    }

    public ArrayList<String> getstrongAreaCount() {

        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < strongAreaList.size(); i++)
            if (strongAreaList.get(i).getTopicNm().length() > 19) {
                label.add(strongAreaList.get(i).getTopicNm().substring(0, 18).concat("..."));
            } else {
                label.add(strongAreaList.get(i).getTopicNm());
            }
        return label;
    }

    public ArrayList<String> getweakAreaCount() {

        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < weakAreaList.size(); i++)
            if (weakAreaList.get(i).getTopicNm().length() > 19) {
                label.add(weakAreaList.get(i).getTopicNm().substring(0, 18).concat("..."));
            } else {
                label.add(weakAreaList.get(i).getTopicNm());
            }
        return label;
    }

}