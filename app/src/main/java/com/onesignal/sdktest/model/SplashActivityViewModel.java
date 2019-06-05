package com.onesignal.sdktest.model;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.onesignal.OneSignal;
import com.onesignal.sdktest.constant.Tag;
import com.onesignal.sdktest.util.IntentTo;

public class SplashActivityViewModel implements ActivityViewModel {

    private IntentTo intentTo;

    private Context context;

    @Override
    public Activity getActivity() {
        return (Activity) context;
    }

    @Override
    public Activity getAppCompatActivity() {
        return (AppCompatActivity) context;
    }

    @Override
    public ActivityViewModel onActivityCreated(Context context) {
        this.context = context;

        intentTo = new IntentTo(context);

        return this;
    }

    @Override
    public ActivityViewModel setupInterfaceElements() {

        setupOneSignalSDK();

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

        Log.d(Tag.DEBUG, "OneSignal SDK initialized");

        intentTo.mainActivity();
    }
}
