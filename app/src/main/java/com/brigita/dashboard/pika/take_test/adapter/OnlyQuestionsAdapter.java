package com.brigita.dashboard.pika.take_test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.brigita.dashboard.pika.R;
import com.brigita.dashboard.pika.databinding.RecViewOnlyQuestionBinding;
import com.brigita.dashboard.pika.take_test.model.OnlyQuestionsModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class OnlyQuestionsAdapter extends RecyclerView.Adapter<OnlyQuestionsAdapter.ViewHolder> {

   private List<OnlyQuestionsModel> onlyQuestionsModels;
   private Context context;

    public OnlyQuestionsAdapter(List<OnlyQuestionsModel> onlyQuestionsModels, Context context) {
        this.onlyQuestionsModels = onlyQuestionsModels;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecViewOnlyQuestionBinding binding;


        public ViewHolder(RecViewOnlyQuestionBinding binding) {
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
    public OnlyQuestionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        RecViewOnlyQuestionBinding binding = RecViewOnlyQuestionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

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
    public void onBindViewHolder(@NonNull OnlyQuestionsAdapter.ViewHolder holder, int position) {

        OnlyQuestionsModel onlyQuestionsModel = onlyQuestionsModels.get(position);

        if (onlyQuestionsModel.dataFormatId == 1) {

            holder.binding.textQuestionTv.setText(onlyQuestionsModel.dataFormatValue);

        } else if (onlyQuestionsModel.dataFormatId == 6) {

            String url = onlyQuestionsModel.dataFormatValue;

            ImageView view = holder.binding.imgQuestionImgView.findViewById(R.id.img_question_img_view);

            Glide.with(context).load(url).into(view);
        }

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {

        if (onlyQuestionsModels == null) {
            return 0;
        } else {
            return onlyQuestionsModels.size();
        }

    }
}
