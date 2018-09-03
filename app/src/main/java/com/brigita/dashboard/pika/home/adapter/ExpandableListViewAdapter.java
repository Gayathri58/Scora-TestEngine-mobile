package com.brigita.dashboard.pika.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.brigita.dashboard.pika.R;
import com.brigita.dashboard.pika.databinding.RecViewExpandableItemBinding;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import java.util.List;

/*
 * Adapter class for Expandable list view in navigation drawer
 * Advanced recycler library
 */

public class ExpandableListViewAdapter extends AbstractExpandableItemAdapter<ExpandableListViewAdapter.GroupViewHolder,
        ExpandableListViewAdapter.ChildViewHolder> {

    public static final String TAG = ExpandableListViewAdapter.class.getSimpleName();

    Context context;
    List<String> organizationTitle, organizationList;
    private ExpandableItemListener expandableItemListener;
    ImageView imageView;

    public ExpandableListViewAdapter(Context context, List<String> organizationTitle, List<String> organizationList) {
        setHasStableIds(true);
        this.context = context;
        this.organizationTitle = organizationTitle;
        this.organizationList = organizationList;
    }

    public interface ExpandableItemListener {
        void onClick(int position);
    }

    public void setExpandableItemListener(ExpandableItemListener expandableItemListener) {
        this.expandableItemListener = expandableItemListener;
    }

    static class GroupViewHolder extends AbstractExpandableItemViewHolder {
        RecViewExpandableItemBinding binding;

        public GroupViewHolder(RecViewExpandableItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    static class ChildViewHolder extends AbstractExpandableItemViewHolder {
        RecViewExpandableItemBinding binding;

        public ChildViewHolder(RecViewExpandableItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
    @Override
    public int getGroupCount() {
        return organizationTitle.size();
    }

    @Override
    public int getChildCount(int groupPosition) {
        return organizationList.size();
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public GroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        RecViewExpandableItemBinding binding = RecViewExpandableItemBinding.inflate(layoutInflater, parent, false);
        return new GroupViewHolder(binding);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        RecViewExpandableItemBinding binding = RecViewExpandableItemBinding.inflate(layoutInflater, parent, false);
        return new ChildViewHolder(binding);
    }

    @Override
    public void onBindGroupViewHolder(GroupViewHolder holder, int groupPosition, int viewType) {

        holder.binding.rvExpandableViewTv.setPadding(24, 16, 0, 16);
        holder.binding.rvExpandableViewTv.setText(organizationTitle.get(groupPosition));

        imageView = holder.binding.rvExpandableViewIv;

    }

    @Override
    public void onBindChildViewHolder(final ChildViewHolder holder, int groupPosition, final int childPosition, int viewType) {
        holder.binding.rvExpandableViewTv.setPadding(48, 8, 0, 8);
        holder.binding.rvExpandableViewTv.setText(organizationList.get(childPosition));
        holder.binding.rvExpandableViewIv.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableItemListener != null) {
                    expandableItemListener.onClick(childPosition);
                }
            }
        });

    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(GroupViewHolder holder, int groupPosition, int x, int y, boolean expand) {

        holder.binding.rvExpandableViewIv.setImageResource(expand ? R.drawable.ic_keyboard_arrow_up_black_24dp
                : R.drawable.ic_keyboard_arrow_down_black_24dp);

        Log.e(TAG, "expand " + expand);

        Log.e(TAG, "groupPosition " + groupPosition);
        return true;
    }

    public void resetArrowIcon() {

        if (imageView != null) {
            imageView.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
        }
    }


}
