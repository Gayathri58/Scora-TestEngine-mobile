package com.brigita.dashboard.pika.dashboard.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PieChartResponse {

    @SerializedName("test_group")
    @Expose
    public List<TestGroup> testGroup = null;
    @SerializedName("test_summary")
    @Expose
    public List<TestSummary> testSummary = null;
    @SerializedName("performance_analysis")
    @Expose
    public List<PerformanceAnalysi> performanceAnalysis = null;
    @SerializedName("recent_activity_log")
    @Expose
    public List<RecentActivityLog> recentActivityLog = null;

    public List<TestGroup> getTestGroup() {
        return testGroup;
    }

    public void setTestGroup(List<TestGroup> testGroup) {
        this.testGroup = testGroup;
    }

    public List<TestSummary> getTestSummary() {
        return testSummary;
    }

    public void setTestSummary(List<TestSummary> testSummary) {
        this.testSummary = testSummary;
    }

    public List<PerformanceAnalysi> getPerformanceAnalysis() {
        return performanceAnalysis;
    }

    public void setPerformanceAnalysis(List<PerformanceAnalysi> performanceAnalysis) {
        this.performanceAnalysis = performanceAnalysis;
    }

    public List<RecentActivityLog> getRecentActivityLog() {
        return recentActivityLog;
    }

    public void setRecentActivityLog(List<RecentActivityLog> recentActivityLog) {
        this.recentActivityLog = recentActivityLog;
    }
}
