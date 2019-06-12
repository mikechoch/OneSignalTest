package com.onesignal.sdktest.model;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;
import com.onesignal.sdktest.R;
import com.onesignal.sdktest.adapter.NotificationRecyclerViewAdapter;
import com.onesignal.sdktest.constant.Text;
import com.onesignal.sdktest.type.Notification;
import com.onesignal.sdktest.ui.RecyclerViewBuilder;
import com.onesignal.sdktest.user.CurrentUser;
import com.onesignal.sdktest.util.Animate;
import com.onesignal.sdktest.util.Font;
import com.onesignal.sdktest.util.IntentTo;
import com.onesignal.sdktest.util.OneSignalPrefs;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivityViewModel implements ActivityViewModel {

    private Animate animate;
    private CurrentUser currentUser;
    private Font font;
    private IntentTo intentTo;
    private OneSignalPrefs oneSignalPrefs;
    private RecyclerViewBuilder recyclerViewBuilder;

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private NestedScrollView nestedScrollView;

    private TextView accountTitleTextView;
    private LinearLayout accountDetailsLinearLayout;
    private TextView notSignedInTextView;
    private ProgressBar loginLogoutButtonProgressBar;
    private Button loginLogoutButton;

    private TextView notificationDemoTitleTextView;
    private RecyclerView notificationRecyclerView;
    private NotificationRecyclerViewAdapter notificationRecyclerViewAdapter;

    private boolean shouldScrollTop = false;

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

        animate = new Animate();
        currentUser = CurrentUser.getInstance();
        font = new Font(context);
        intentTo = new IntentTo(context);
        oneSignalPrefs = OneSignalPrefs.getInstance(context);
        recyclerViewBuilder = new RecyclerViewBuilder(context);

        appBarLayout = getActivity().findViewById(R.id.main_activity_app_bar_layout);
        toolbar = getActivity().findViewById(R.id.main_activity_toolbar);
        nestedScrollView = getActivity().findViewById(R.id.main_activity_nested_scroll_view);

        accountTitleTextView = getActivity().findViewById(R.id.main_activity_account_title_text_view);
        accountDetailsLinearLayout = getActivity().findViewById(R.id.main_activity_account_details_linear_layout);
        notSignedInTextView = getActivity().findViewById(R.id.main_activity_account_not_signed_in_text_view);
        loginLogoutButtonProgressBar = getActivity().findViewById(R.id.login_activity_login_logout_button_progress_button);
        loginLogoutButton = getActivity().findViewById(R.id.main_activity_account_login_logout_button);

        notificationDemoTitleTextView = getActivity().findViewById(R.id.main_activity_notification_demo_title_text_view);
        notificationRecyclerView = getActivity().findViewById(R.id.main_activity_notification_recycler_view);

        return this;
    }

    @Override
    public ActivityViewModel setupInterfaceElements() {
        font.applyFont(accountTitleTextView, font.saralaBold);
        font.applyFont(notSignedInTextView, font.saralaBold);
        font.applyFont(loginLogoutButton, font.saralaBold);
        font.applyFont(notificationDemoTitleTextView, font.saralaBold);

        setupScrollView();
        setupAccountLayout();
        setupNotificationDemoLayout();

        return this;
    }

    private void setupAccountLayout() {


        //TODO: Handle toggle login state
        boolean isSignedIn = currentUser.isSignedIn();
        animate.toggleAnimationView(!isSignedIn, View.GONE, accountDetailsLinearLayout, notSignedInTextView);
        if (isSignedIn) {
            loginLogoutButton.setText(Text.LOGOUT);
        } else {
            loginLogoutButton.setText(Text.LOGIN);
        }

        loginLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser.isSignedIn()) {
                    // Logout handling
                    animate.toggleAnimationView(true, View.GONE, loginLogoutButton, loginLogoutButtonProgressBar);
                    logoutEmail();
                } else {
                    // Login handling
                    intentTo.loginActivity();
                }
            }
        });
    }

    @Override
    public void setupToolbar() {
        toolbar.setTitle(Text.EMPTY);
        getAppCompatActivity().setSupportActionBar(toolbar);
    }

    @Override
    public void networkConnected() {

    }

    @Override
    public void networkDisconnected() {

    }

    private void setupNotificationDemoLayout() {
        recyclerViewBuilder.setupRecyclerView(notificationRecyclerView, 16, false, true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        notificationRecyclerView.setLayoutManager(gridLayoutManager);

        notificationRecyclerViewAdapter = new NotificationRecyclerViewAdapter(context, Notification.values());
        notificationRecyclerView.setAdapter(notificationRecyclerViewAdapter);
    }

    private void setupScrollView() {
        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = nestedScrollView.getScrollY();
                shouldScrollTop = scrollY != 0;
            }
        });
    }

    private void logoutEmail() {
        OneSignal.logoutEmail(new OneSignal.EmailUpdateHandler() {
            @Override
            public void onSuccess() {
                currentUser.setEmail(null);
                oneSignalPrefs.clearCachedEmail();
                intentTo.loginActivity();
            }

            @Override
            public void onFailure(OneSignal.EmailUpdateError error) {
                //TODO: Show error logging out
                animate.toggleAnimationView(false, View.GONE, loginLogoutButton, loginLogoutButtonProgressBar);
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
            JSONObject notificationContent = new JSONObject(
                    "{'contents': {'en': 'The notification message or body'}," +
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
        return shouldScrollTop;
    }
}
