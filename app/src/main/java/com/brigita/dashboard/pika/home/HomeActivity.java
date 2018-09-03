package com.brigita.dashboard.pika.home;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.brigita.dashboard.pika.R;
import com.brigita.dashboard.pika.SessionManager;
import com.brigita.dashboard.pika.authentication.model.Application;
import com.brigita.dashboard.pika.authentication.model.AvailableModule;
import com.brigita.dashboard.pika.authentication.model.AvailableOrganization;
import com.brigita.dashboard.pika.authentication.model.UserDetailsResponse;
import com.brigita.dashboard.pika.databinding.ActivityHomeBinding;
import com.brigita.dashboard.pika.home.adapter.ExpandableListViewAdapter;
import com.brigita.dashboard.pika.home.adapter.NavMenuListAdapter;
import com.brigita.dashboard.pika.home.model.NavigationMenuModel;
import com.brigita.dashboard.pika.performance_insights.TabHostFragment;
import com.brigita.dashboard.pika.retrofit.RetrofitService;
import com.brigita.dashboard.pika.retrofit.RetrofitServiceGenerator;
import com.brigita.dashboard.pika.scheduled_tests.ScheduledTestsFragment;
import com.brigita.dashboard.pika.test_history.TestHistoryFragment;
import com.bumptech.glide.Glide;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private ActivityHomeBinding homeBinding;
    private Toolbar toolbar;
    private SessionManager sessionManager;
    private ActionBarDrawerToggle mDrawerToggle;
    private List<NavigationMenuModel> navigationMenuList = new ArrayList<>();
    private List<String> organizationList = new ArrayList<>();
    private UserDetailsResponse userDetailsResponse;
    private List<String> organizationTitle = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView orgNameTv, userNameTv;
    ProgressBar progressBar ;

    Boolean navHome = false ;

    ImageView orgIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        progressBar= homeBinding.progressBarHome.findViewById(R.id.progress_bar_home);

        orgIcon = findViewById(R.id.org_icon);

        sessionManager = new SessionManager(this);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       //  getSupportActionBar().setDisplayShowTitleEnabled(false);

        checkInternetAccess();

        setupToolbar();

        mDrawerToggle = new ActionBarDrawerToggle(this, homeBinding.drawerLayout, toolbar, R.string.app_name, R.string.app_name);

     /*   getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
*/
        homeBinding.navListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        getUserDetails();


      /*  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/

      /*  NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if (navHome){
            navHome = false;
            Fragment fragment;
            fragment = new TabHostFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        } else {

         // super.onBackPressed();

            new AlertDialog.Builder(this).
                    setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            })//.setTitle("Exit ?")
                    .setMessage("Are you sure you want to Exit ?").create().show();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    View mView = null;

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            if (!navigationMenuList.get(position).name.equals("Logout")) {
                if (mView != null) {
                    mView.setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.color_nav_drawer_bg));
                }
                view.setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.color_nav_drawer_bg_active));
                mView = view;
            }

            Log.e(TAG, "clicked position " + position);

            selectItem(position);

        }
    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (navigationMenuList.get(position).name) {

            case "Dashboard": {
                checkInternetAccess();
                navHome = false;
                progressBar.setVisibility(View.VISIBLE);
                fragment = new TabHostFragment();
                // fragment = new DashboardFragment();
                break;
            }
            case "Scheduled Test": {
                checkInternetAccess();
                navHome = true;
                fragment = new ScheduledTestsFragment();
                break;
            }
            case "Test History": {
                checkInternetAccess();
                navHome = true;
                fragment = new TestHistoryFragment();
                break;
            }
            case "Reports": {
                break;
            }
            case "Settings": {
                break;
            }
            case "Logout": {
                checkInternetAccess();
                logOutDialog();
                break;
            }
        }

        if (fragment != null) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        }

        homeBinding.navListView.setItemChecked(position, true);
        homeBinding.navListView.setSelection(position);
        homeBinding.drawerLayout.closeDrawer(homeBinding.navListViewContainer);
    }

    private void checkInternetAccess() {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert conMgr != null;
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null) {
            new AlertDialog.Builder(HomeActivity.this).setMessage("Check the Internet Connection").show();
            finish();
        }
    }

    private void logOutDialog() {

        new AlertDialog.Builder(this).
                setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sessionManager.logoutUser();
            }
        })//.setTitle("Logout ?")
                .setCancelable(false)
                .setMessage("Are you sure you want to Logout?").create().show();

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mDrawerToggle != null) {
            mDrawerToggle.syncState();
        }
    }

    void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
       /* getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_button_of_three_horizontal_lines);
*/
    }

    void setupDrawerToggle() {


       mDrawerToggle.syncState();


    }

    void getUserDetails() {

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(this);

        Call<UserDetailsResponse> userDetailsCall = retrofitService.getUserDetails();

        userDetailsCall.enqueue(new Callback<UserDetailsResponse>() {
            @Override
            public void onResponse(Call<UserDetailsResponse> call, Response<UserDetailsResponse> response) {

                Log.e(TAG, "code" + response.code());

                if (response.isSuccessful()) {

                    userDetailsResponse = response.body();

                    Log.e(TAG, "profileEmail " + userDetailsResponse.userLoginDetails.profileEmail);

                    Log.e(TAG, "Spinner size  " + organizationList.size());

                    userNameTv = homeBinding.navHeaderHome.findViewById(R.id.nav_header_name_tv);
                    userNameTv.setText(userDetailsResponse.userLoginDetails.profileName);

                    setUpSingleOrMultipleOrg();

                }
            }

            @Override
            public void onFailure(Call<UserDetailsResponse> call, Throwable t) {

                Log.e(TAG, "getCause " + t.getCause());

            }
        });
    }

    void setUpSingleOrMultipleOrg() {

        if (userDetailsResponse.userLoginDetails.availableOrganization.size() > 1) {

            recyclerView = homeBinding.navHeaderHome.findViewById(R.id.nav_header_org_rv);
            recyclerView.setVisibility(View.VISIBLE);

            organizationTitle.clear();

            navigationMenuList.clear();

            organizationTitle.add(userDetailsResponse.userLoginDetails.profileOrganizationText);

            for (AvailableOrganization organization : userDetailsResponse.userLoginDetails.availableOrganization) {

                organizationList.add(organization.organizationName);
            }

            // Setup expandable feature and RecyclerView

            final RecyclerViewExpandableItemManager expMgr = new RecyclerViewExpandableItemManager(null);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            final ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(this, organizationTitle, organizationList);

            recyclerView.setAdapter(expMgr.createWrappedAdapter(adapter));

            // NOTE: need to disable change animations to ripple effect work properly

            ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

            expMgr.attachRecyclerView(recyclerView);

            adapter.setExpandableItemListener(new ExpandableListViewAdapter.ExpandableItemListener() {
                @Override
                public void onClick(int position) {

                    Log.e(TAG, "Expandable Adapter position " + position);

                    if (!organizationTitle.get(0).equals(organizationList.get(position))) {

                        organizationTitle.clear();

                        navigationMenuList.clear();

                        organizationTitle.add(organizationList.get(position));
                        Log.e(TAG, "organizationTitle " + organizationTitle.get(0));

                        adapter.notifyDataSetChanged();


                        organizationSelection(organizationTitle.get(0));
                    }

                    expMgr.collapseGroup(0);
                    adapter.resetArrowIcon();

                }
            });

        } else {
            orgNameTv = homeBinding.navHeaderHome.findViewById(R.id.nav_header_org_tv);
            orgNameTv.setText(userDetailsResponse.userLoginDetails.profileOrganizationText);
            orgNameTv.setVisibility(View.VISIBLE);

        }

        organizationSelection(userDetailsResponse.userLoginDetails.profileOrganizationText);

    }

    /**
     * @param organizationName sets the organization given in the parameter
     */

    void organizationSelection(String organizationName) {

        Log.e(TAG, "organizationSelection started ");

        for (AvailableOrganization organization : userDetailsResponse.userLoginDetails.availableOrganization) {

            if (organization.organizationName.equals(organizationName)) {
                Log.e(TAG, "name equals org name ");

                String url = organization.organizationImg ;

                Glide   .with(this)
                        .load(url)
                        .into(orgIcon);

                sessionManager.setOrgId(organization.orgId);

                for (Application application : organization.applications) {

                    if ("User Dashboard".equals(application.applicationName)) {

                        Log.e(TAG, "Dashboard size  " + application.availableModules.size());

                        Log.e("module", String.valueOf(application.availableModules));

                        for (AvailableModule availableModule : application.availableModules) {

                            if (availableModule.access) {

                                switch (availableModule.moduleName) {

                                    case "Dashboard": {
                                        NavigationMenuModel navigationMenu = new NavigationMenuModel(R.drawable.ic_chart_bar_white_36dp, availableModule.moduleName);
                                        navigationMenuList.add(navigationMenu);
                                        break;
                                    }
                                    case "Scheduled Test": {
                                        NavigationMenuModel navigationMenu = new NavigationMenuModel(R.drawable.ic_calender_white, availableModule.moduleName);
                                        navigationMenuList.add(navigationMenu);
                                        break;
                                    }
                                    case "Test History": {
                                        NavigationMenuModel navigationMenu = new NavigationMenuModel(R.drawable.ic_history_white_36dp, availableModule.moduleName);
                                        navigationMenuList.add(navigationMenu);
                                        break;
                                    }


                                    /*case "Reports": {
                                        NavigationMenuModel navigationMenu =
                                                new NavigationMenuModel(
                                                        R.drawable.ic_file_chart_white_36dp,
                                                        availableModule.moduleName);
                                        navigationMenuList.add(navigationMenu);
                                        }
                                        */
                                }

                            }

                        }

                            //settings and logout are default for all organizations

                        /*NavigationMenuModel navigationMenuSetting =
                                new NavigationMenuModel(
                                        R.drawable.ic_settings_white_36dp,
                                        "Settings");
                        navigationMenuList.add(navigationMenuSetting);*/

                            NavigationMenuModel navigationMenuLogout = new NavigationMenuModel(R.drawable.ic_logout_white_36dp, "Logout");
                            navigationMenuList.add(navigationMenuLogout);

                            Log.e(TAG, "size  " + navigationMenuList.size());

                            setUpDrawer();


                    }
                }
            }
        }

    }

    void setUpDrawer() {

        NavMenuListAdapter listAdapter = new NavMenuListAdapter(this, R.layout.list_view_nav_item_row, navigationMenuList);

        homeBinding.navListView.setAdapter(listAdapter);

        homeBinding.navListView.setOnItemClickListener(new DrawerItemClickListener());

        homeBinding.drawerLayout.addDrawerListener(mDrawerToggle);

        setupDrawerToggle();

        //sets first item as default
        selectItem(0);
    }

}
