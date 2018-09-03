package com.brigita.dashboard.pika.performance_insights;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brigita.dashboard.pika.R;
import com.brigita.dashboard.pika.dashboard.DashboardFragment;
import com.brigita.dashboard.pika.databinding.FragmentTabhostBinding;

import java.util.ArrayList;
import java.util.List;

public class TabHostFragment extends Fragment {

    FragmentTabhostBinding binding;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    public TabHostFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tabhost, container, false);


      /*  toolbar = binding.toolbar.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        viewPager = binding.viewpager.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = binding.tabs.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return binding.getRoot();
    }



    public void setupViewPager(ViewPager upViewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new DashboardFragment(), "Performance Summary");
        adapter.addFragment(new PerformanceInsightsFragment(), "Performance Insights");
        upViewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {

            super(fm);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
