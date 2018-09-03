package com.brigita.dashboard.pika.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

// import com.brigita.dashboard.pika.databinding.ListViewNavItemRowBinding;
import com.brigita.dashboard.pika.databinding.ListViewNavItemRowBinding;
import com.brigita.dashboard.pika.home.model.NavigationMenuModel;

import java.util.List;

/*
 * adapter class for the list view in
 * navigation drawer
 */

public class NavMenuListAdapter extends ArrayAdapter<NavigationMenuModel> {

    private Context context;
    private int resourceId;
    private List<NavigationMenuModel> navigationMenuModels;

    public NavMenuListAdapter(@NonNull Context context, int resourceId, @NonNull List<NavigationMenuModel>  navigationMenuModels) {

        super(context, resourceId, navigationMenuModels);
        this.context = context;
        this.resourceId = resourceId;
        this.navigationMenuModels = navigationMenuModels;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ListViewNavItemRowBinding listBinding;

        if (convertView == null) {

            LayoutInflater layoutInflater = LayoutInflater.from(context);

           listBinding = ListViewNavItemRowBinding.inflate(layoutInflater, parent, false);

            convertView = listBinding.getRoot();

            convertView.setTag(listBinding);

        } else {
            listBinding = (ListViewNavItemRowBinding) convertView.getTag();
        }


        NavigationMenuModel item = navigationMenuModels.get(position);

        listBinding.listViewNavTextView.setText(item.name);
        listBinding.listViewNavImageView.setImageResource(item.icon);

        return convertView;
    }
}