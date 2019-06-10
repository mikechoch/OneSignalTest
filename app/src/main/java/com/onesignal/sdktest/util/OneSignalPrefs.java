package com.onesignal.sdktest.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.onesignal.sdktest.constant.Key;
import com.onesignal.sdktest.constant.Text;

public class OneSignalPrefs {

    private static OneSignalPrefs oneSignalPrefs;

    private SharedPreferences sharedPreferences;


    public boolean exists(String key) {
        return sharedPreferences.contains(key);
    }

    public String getCachedUserEmail() {
        return sharedPreferences.getString(Key.USER_EMAIL_SHARED_PREF, Text.EMPTY);
    }

    public void cacheUserEmail(String email) {
        sharedPreferences.edit().putString(Key.USER_EMAIL_SHARED_PREF, email).apply();
    }

    public void clearCachedEmail() {
        sharedPreferences.edit().remove(Key.USER_EMAIL_SHARED_PREF).apply();
    }

    private OneSignalPrefs(Context context) {
        sharedPreferences = context.getSharedPreferences("com.onesignal.sdktest", Context.MODE_PRIVATE);
    }

    public static OneSignalPrefs getInstance(Context context) {
        if (oneSignalPrefs == null) {
            oneSignalPrefs = new OneSignalPrefs(context);
        }
        return oneSignalPrefs;
    }
}
