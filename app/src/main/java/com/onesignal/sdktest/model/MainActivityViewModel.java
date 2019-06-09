package com.onesignal.sdktest.model;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;
import com.onesignal.sdktest.R;
import com.onesignal.sdktest.constant.Tag;
import com.onesignal.sdktest.constant.Text;
import com.onesignal.sdktest.user.CurrentUser;
import com.onesignal.sdktest.util.Animate;
import com.onesignal.sdktest.util.ProfileUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivityViewModel implements ActivityViewModel {

    private Animate animate;
    private CurrentUser currentUser;
    private ProfileUtil profileUtil;

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private NestedScrollView nestedScrollView;
    private TextInputLayout emailTextInputLayout;
    private TextInputEditText emailTextInputEditText;

    private RelativeLayout loginButton;
    private TextView loginButtonTextView;
    private ProgressBar loginButtonProgressBar;

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
        profileUtil = new ProfileUtil();

        appBarLayout = getActivity().findViewById(R.id.main_activity_app_bar_layout);
        toolbar = getActivity().findViewById(R.id.main_activity_toolbar);
        nestedScrollView = getActivity().findViewById(R.id.main_activity_nested_scroll_view);
        emailTextInputLayout = getActivity().findViewById(R.id.email_sign_in_text_input_layout);
        emailTextInputEditText = getActivity().findViewById(R.id.email_sign_in_text_input_edit_text);
        loginButton = getActivity().findViewById(R.id.email_sign_in_login_button_relative_layout);
        loginButtonTextView = getActivity().findViewById(R.id.email_sign_in_login_button_text_view);
        loginButtonProgressBar = getActivity().findViewById(R.id.email_sign_in_login_button_progress_bar);

        setupOneSignalSDK();

        return this;
    }

    @Override
    public ActivityViewModel setupInterfaceElements() {
        setupScrollView();
        setupSignInButton();

        return this;
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

    private void setupOneSignalSDK() {
        // OneSignal Initialization
        OneSignal.startInit(context)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        Log.d(Tag.DEBUG, Text.ONESIGNAL_SDK_INIT);
    }

    private void setupNotificationButtonLayout() {




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

    private void setupSignInButton() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser.getEmail() != null) {
                    emailTextInputEditText.setEnabled(true);
                    emailTextInputEditText.setText(Text.EMPTY);

                    currentUser.setEmail(null);

                    loginButtonTextView.setText(Text.LOGIN);
                } else {
                    animate.toggleAnimationView(true, loginButtonTextView, loginButtonProgressBar);
                    attemptLogin();
                }
            }

            private void attemptLogin() {
                if (profileUtil.isEmailValid(emailTextInputLayout) && emailTextInputEditText.getText() != null) {
                    String email = emailTextInputEditText.getText().toString().trim();
                    signInEmail(email);
                } else {
                    animate.toggleAnimationView(false, loginButtonTextView, loginButtonProgressBar);
                }
            }
        });
    }

    /**
     *
     * @param email
     */
    private void signInEmail(final String email) {
        OneSignal.setEmail(email, new OneSignal.EmailUpdateHandler() {
            @Override
            public void onSuccess() {
                emailTextInputEditText.setEnabled(false);

                currentUser.setEmail(email);

                loginButtonTextView.setText(Text.LOGOUT);

                animate.toggleAnimationView(false, loginButtonTextView, loginButtonProgressBar);
                Log.d(Tag.DEBUG, Text.EMAIL_LOGIN_SUCCESSFUL);
            }

            @Override
            public void onFailure(OneSignal.EmailUpdateError error) {
                animate.toggleAnimationView(false, loginButtonTextView, loginButtonProgressBar);
                Log.e(Tag.ERROR, error.getMessage());
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
