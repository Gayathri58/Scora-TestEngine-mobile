package com.brigita.dashboard.pika.test_history.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.brigita.dashboard.pika.Const.Const;
import com.brigita.dashboard.pika.R;
import com.brigita.dashboard.pika.databinding.RecViewDashboardTestHistoryBinding;
import com.brigita.dashboard.pika.test_history.areachart.AreaChartTestHistory;
import com.brigita.dashboard.pika.test_history.areachart.model.AreaChartRes;
import com.brigita.dashboard.pika.test_history.model.TestDtl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BRIGITA on 04-07-2018.
 */

public class TestHistoryAdapter extends RecyclerView.Adapter<TestHistoryAdapter.ViewHolder> {

    /*
     * Recycler view Adapter class for test history
     * shown in test history
     */

    private List<TestDtl> testDtlList;
    private Context context;
    private ImageView areaChartTestHistory;
    ArrayList<AreaChartRes> areaChartResArrayList = new ArrayList<>();
    List<String> attemptList;
    List<String> srtTestName;
    List<String> srtTestDate;
    List<String> srtPercentScore;

    public TestHistoryAdapter(List<String> attemptList, List<String> srtTestName, List<String> srtTestDate, List<String> srtPercentScore, Context context) {
        this.attemptList = attemptList;
        this.srtTestName = srtTestName;
        this.srtTestDate = srtTestDate;
        this.srtPercentScore = srtPercentScore;
        this.context = context;
    }

    public TestHistoryAdapter(List<AreaChartRes> areaChartResList, Context context) {
        this.areaChartResArrayList = (ArrayList<AreaChartRes>) areaChartResList;
        this.context = context;


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecViewDashboardTestHistoryBinding binding;

        public ViewHolder(RecViewDashboardTestHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    /**
     * Called when RecyclerView needs a new {@link RecyclerView.ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(RecyclerView.ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(RecyclerView.ViewHolder, int)
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecViewDashboardTestHistoryBinding binding = RecViewDashboardTestHistoryBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false);

        areaChartTestHistory = binding.areaChartImgView.findViewById(R.id.area_chart_img_view);

        return new ViewHolder(binding);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link RecyclerView.ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link RecyclerView.ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(RecyclerView.ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        AreaChartRes areaChartRes = areaChartResArrayList.get(position);

        String testName = "Test Name : " + "<b>" + areaChartRes.testName + "</b>";
        holder.binding.dashboardTestHistorySubjectNameTv.setText(Html.fromHtml(testName));

        String endDate = "End Date : " + "<b>" + areaChartRes.endTime.toUpperCase() + "</b>";
        holder.binding.dashboardTestHistoryTestDate.setText(Html.fromHtml(endDate));

        String percentScore = "Result : " + "<b>" + areaChartRes.percentScore + "/100" + "</b>";
        holder.binding.testHistoryPercentScore.setText(Html.fromHtml(percentScore));

        String attemptCount = "Attempt : " + "<b>" + areaChartRes.currentAttempt + "</b>";
        holder.binding.testHistoryAttemptCountTv.setText(Html.fromHtml(attemptCount));


/*

        String testName = "<b>" + "Test Name : " + "</b>" + areaChartResArrayList.get(position).getTestName();
        holder.binding.dashboardTestHistorySubjectNameTv.setText(Html.fromHtml(testName));

        String endDate = "<b>" + "End Date : " + "</b>" + areaChartResArrayList.get(position).getEndTime().toUpperCase();
        holder.binding.dashboardTestHistoryTestDate.setText(Html.fromHtml(endDate));

        String percentScore = "<b>" + "Results : " + "</b>" + areaChartResArrayList.get(position).getPercentScore()+ "/100";
        holder.binding.testHistoryPercentScore.setText(Html.fromHtml(percentScore));
*/

        areaChartTestHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Const.currentAttempt = areaChartResArrayList.get(position).getCurrentAttempt();
                Const.test_id = areaChartResArrayList.get(position).getTestId();
                Const.schedule_id = areaChartResArrayList.get(position).getScheduleId();

                context.startActivity(new Intent(context, AreaChartTestHistory.class));

            }
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if (areaChartResArrayList == null) {
            return 0;
        } else {
            return areaChartResArrayList.size();
        }
    }

}
