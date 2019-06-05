package com.onesignal.sdktest.model;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;
import com.onesignal.sdktest.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivityViewModel implements ActivityViewModel {

    private ScrollView scrollView;

    private boolean isScrollTop = false;

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

        scrollView = getActivity().findViewById(R.id.main_activity_scroll_view);

        return this;
    }

    @Override
    public ActivityViewModel setupInterfaceElements() {
        setupScrollView();

        return this;
    }

    private void setupScrollView() {
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY();
                isScrollTop = scrollY == 0;
            }
        });
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

    private void setupSignInButton() {

    }

    /**
     *
     * @param email
     */
    private void signInEmail(String email) {
        OneSignal.setEmail(email, new OneSignal.EmailUpdateHandler() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(OneSignal.EmailUpdateError error) {

            }
        });
    }

    private void sendDeviceNotification() {
        OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
        String userId = status.getSubscriptionStatus().getUserId();
        boolean isSubscribed = status.getSubscriptionStatus().getSubscribed();

        if (!isSubscribed)
            return;

        try {
            JSONObject notificationContent = new JSONObject("{'contents': {'en': 'The notification message or body'}," +
                    "'include_player_ids': ['" + userId + "']}");
            OneSignal.postNotification(notificationContent, new OneSignal.PostNotificationResponseHandler() {
                @Override
                public void onSuccess(JSONObject response) {

                }

                @Override
                public void onFailure(JSONObject response) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean shouldScrollToTop() {
        return isScrollTop;
    }
}
