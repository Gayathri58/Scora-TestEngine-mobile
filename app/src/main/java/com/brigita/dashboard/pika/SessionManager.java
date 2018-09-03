package com.brigita.dashboard.pika;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.brigita.dashboard.pika.authentication.LoginActivity;

/*
 * Used to Manage the user session
 */

public class SessionManager {


    // Shared Preferences
    private static SharedPreferences mSharedPref;

    // Context
    private Context mContext;

    private SharedPreferences.Editor editor;


    // Sharedpref file name
    private static final String PREF_NAME = "pikaAndroidSharedPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_ACCESS_TOKEN = "accessToken";

    public static final String KEY_DEFAULT_ORG_ID = "default_org_id";

    public static final String KEY_TEST_ID = "test_id";

    public static final String KEY_CURRENT_ATTEMPT ="current_attempt";

    public static final String KEY_TEST_INSTANCE_ID ="test_instance_id";

    public static final String KEY_SCHEDULE_ID = "schedule_id";

    public static final String KEY_CUS_CREATED_AT = "customerCreatedAt";

    public static final String KEY_CUS_EMAIL = "customerEmail";

    public static final String KEY_CUS_FIRST_NAME = "customerFirstName";

    public static final String KEY_CUS_LAST_NAME = "customerLastName";

    public static final String KEY_CUS_GENDER = "customerGender";

    public static final String KEY_CUS_MOBILE = "customerMobile";

    public static final String KEY_IS_WAITING_FOR_SMS = "IsWaitingForSms";

    public SessionManager(Context context) {
        this.mContext = context;
        mSharedPref = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = mSharedPref.edit();
    }


    /**
     * Create login session
     */
    public void createLoginSession(String accessToken) {

        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        // Storing token in pref
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        // commit changes
        editor.apply();
    }


    public String getAccessToken() {

        return mSharedPref.getString(KEY_ACCESS_TOKEN, null);
    }


    public void setOrgId(int ordId) {

        // Storing org id in pref
        editor.putInt(KEY_DEFAULT_ORG_ID, ordId);
        // commit changes
        editor.apply();
    }

    public int getOrgId() {
        // return user
        return mSharedPref.getInt(KEY_DEFAULT_ORG_ID, 0);
    }

    public void setTestId (int testId){
        // Storing test id in pref
        editor.putInt(KEY_TEST_ID, testId);
        // commit changes
        editor.apply();
    }

    public int getTestId(){
        // return user
        return mSharedPref.getInt(KEY_TEST_ID, 0);
    }

    public void setCurrentAttempt (int currentAttempt){
        // Storing current attempt in pref
        editor.putInt(KEY_CURRENT_ATTEMPT, currentAttempt);
        // commit changes
        editor.apply();
    }

    public int getCurrentAttempt(){
        return mSharedPref.getInt(KEY_CURRENT_ATTEMPT, 0);
    }

    public void setTestInstanceId (int testInstanceId) {
        // Storing test instance in pref
        editor.putInt(KEY_TEST_INSTANCE_ID, testInstanceId);
        // commit changes
        editor.apply();
    }

    public int getTestInstanceId (){
        return mSharedPref.getInt(KEY_TEST_INSTANCE_ID,0);
    }

    public void setScheduleId (int scheduleId){

        editor.putInt(KEY_SCHEDULE_ID, scheduleId);

        editor.apply();
    }

    public int getScheduleId(){
        return mSharedPref.getInt(KEY_SCHEDULE_ID,0);
    }

    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(mContext, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            mContext.startActivity(i);
        }

    }


    public void logoutUser() {
        // Clearing all data from Shared Preferences

        editor.clear();
        editor.apply();


        // After logout redirect user to Loing Activity
        Intent i = new Intent(mContext, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Staring Login Activity
        mContext.startActivity(i);
    }

    /**
     *  session status
     */
    public boolean isLoggedIn() {
        return mSharedPref.getBoolean(IS_LOGIN, false);
    }


}
