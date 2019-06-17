package com.onesignal.sdktest.model;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;
import com.onesignal.sdktest.callback.EmailUpdateCallback;
import com.onesignal.sdktest.constant.Key;
import com.onesignal.sdktest.constant.Tag;
import com.onesignal.sdktest.constant.Text;
import com.onesignal.sdktest.user.CurrentUser;
import com.onesignal.sdktest.util.OneSignalPrefs;

public class SplashActivityViewModel implements ActivityViewModel {

    private CurrentUser currentUser;
    private OneSignalPrefs oneSignalPrefs;

    private Context context;


    @Override
    public Activity getActivity() {
        return (Activity) context;
    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        return (AppCompatActivity) context;
    }

    @Override
    public ActivityViewModel onActivityCreated(Context context) {
        this.context = context;

        currentUser = CurrentUser.getInstance();
        oneSignalPrefs = OneSignalPrefs.getInstance(context);

        setupOneSignalSDK();

        return this;
    }

    @Override
    public ActivityViewModel setupInterfaceElements() {

        return this;
    }

    @Override
    public void setupToolbar() {

    }

    @Override
    public void networkConnected() {

    }

    @Override
    public void networkDisconnected() {

    }

    private void setupOneSignalSDK() {
        // OneSignal Initialization
        OneSignal.startInit(context)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        Log.d(Tag.DEBUG, Text.ONESIGNAL_SDK_INIT);

        boolean isSubscribed = oneSignalPrefs.getCachedSubscriptionStatus();
        OneSignal.setSubscription(isSubscribed);
        Log.d(Tag.DEBUG, Text.SUBSCRIPTION_STATUS_SET + ": " + isSubscribed);
    }

    public boolean attemptSignIn(EmailUpdateCallback callback) {
        boolean isEmailCached = oneSignalPrefs.exists(Key.USER_EMAIL_SHARED_PREF);
        if (isEmailCached) {
            String email = oneSignalPrefs.getCachedUserEmail();
            currentUser.signIn(email, callback);
        }
        return isEmailCached;
    }

}
