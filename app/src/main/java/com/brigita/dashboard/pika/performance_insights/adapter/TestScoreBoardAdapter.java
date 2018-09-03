package com.brigita.dashboard.pika.performance_insights.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.brigita.dashboard.pika.performance_insights.model.TestScoreBoardResponse;
import com.brigita.dashboard.pika.databinding.RecViewScoreBoardTableBinding;

import java.util.List;

public class TestScoreBoardAdapter extends RecyclerView.Adapter<TestScoreBoardAdapter.ViewHolder> {

    /*
     * Recycler view Adapter class for dash board
     * shown in dashboard
     */

    private List<TestScoreBoardResponse> testScoreBoardResponseList;
    private Context context;

    public TestScoreBoardAdapter(List<TestScoreBoardResponse> testScoreBoardResponseList, Context context) {
        this.testScoreBoardResponseList = testScoreBoardResponseList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecViewScoreBoardTableBinding binding;

        public ViewHolder(RecViewScoreBoardTableBinding binding) {
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
    public TestScoreBoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecViewScoreBoardTableBinding binding = RecViewScoreBoardTableBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false);

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
    public void onBindViewHolder(@NonNull TestScoreBoardAdapter.ViewHolder holder, int position) {

        TestScoreBoardResponse testScoreBoardResponse = testScoreBoardResponseList.get(position);

        holder.binding.scoreboardTestnameTv.setText(testScoreBoardResponse.testName);
        holder.binding.scoreboardAttemptsTv.setText(testScoreBoardResponse.attempt);
        holder.binding.scoreboardSectionnameTv.setText(testScoreBoardResponse.sectionNm);
        holder.binding.scoreboardNoOfItemsTv.setText(Integer.toString(testScoreBoardResponse.noOfItems));

        holder.binding.scoreboardMaxScoreTv.setText(Integer.toString(testScoreBoardResponse.maxScore));
        holder.binding.scoreboardScoreTv.setText(testScoreBoardResponse.score);
        holder.binding.scoreboardPresentScoreTv.setText(testScoreBoardResponse.percentageScore);

        holder.binding.scoreboardIncorrentItemTv.setText(testScoreBoardResponse.incorrectItems);
        holder.binding.scoreboardCorrentItemTv.setText(testScoreBoardResponse.correctItems);
        holder.binding.scoreboardMissedItemTv.setText(testScoreBoardResponse.missedItems);

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if (testScoreBoardResponseList == null) {
            return 0;
        } else {
            return testScoreBoardResponseList.size();
        }
    }

}
