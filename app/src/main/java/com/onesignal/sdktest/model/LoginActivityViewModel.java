package com.onesignal.sdktest.model;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.onesignal.OneSignal;
import com.onesignal.sdktest.R;
import com.onesignal.sdktest.constant.Key;
import com.onesignal.sdktest.constant.Tag;
import com.onesignal.sdktest.constant.Text;
import com.onesignal.sdktest.user.CurrentUser;
import com.onesignal.sdktest.util.Animate;
import com.onesignal.sdktest.util.Font;
import com.onesignal.sdktest.util.IntentTo;
import com.onesignal.sdktest.util.OneSignalPrefs;
import com.onesignal.sdktest.util.ProfileUtil;

public class LoginActivityViewModel implements ActivityViewModel {

    private Animate animate;
    private CurrentUser currentUser;
    private Font font;
    private IntentTo intentTo;
    private OneSignalPrefs oneSignalPrefs;
    private ProfileUtil profileUtil;

    private TextView loginTitleTextView;
    private TextInputLayout emailTextInputLayout;
    private TextInputEditText emailTextInputEditText;
    private ProgressBar loginButtonProgressBar;
    private Button loginButton;
    private TextView continueToDemoButton;

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
        profileUtil = new ProfileUtil();

        loginTitleTextView = getActivity().findViewById(R.id.login_activity_title_text_view);
        emailTextInputLayout = getActivity().findViewById(R.id.login_activity_email_text_input_layout);
        emailTextInputEditText = getActivity().findViewById(R.id.login_activity_email_text_input_edit_text);
        loginButtonProgressBar = getActivity().findViewById(R.id.login_activity_login_button_progress_button);
        loginButton = getActivity().findViewById(R.id.login_activity_login_button);
        continueToDemoButton = getActivity().findViewById(R.id.login_activity_continue_to_demo_text_view);

        return this;
    }

    @Override
    public ActivityViewModel setupInterfaceElements() {
        font.applyFont(loginTitleTextView, font.saralaBold);
        font.applyFont(emailTextInputLayout, font.saralaRegular);
        font.applyFont(emailTextInputEditText, font.saralaRegular);
        font.applyFont(loginButton, font.saralaBold);
        font.applyFont(continueToDemoButton, font.saralaRegular);

        setupSignInButton();
        setupContinueToDemoButton();

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

    private void setupSignInButton() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser.getEmail() != null) {
                    emailTextInputEditText.setEnabled(true);
                    emailTextInputEditText.setText(Text.EMPTY);

                    currentUser.setEmail(null);

                } else {
                    animate.toggleAnimationView(true, View.GONE, loginButton, loginButtonProgressBar);
                    attemptLogin();
                }
            }

            private void attemptLogin() {
                if (profileUtil.isEmailValid(emailTextInputLayout) && emailTextInputEditText.getText() != null) {
                    String email = emailTextInputEditText.getText().toString().trim();
                    signInEmail(email);
                } else {
                    animate.toggleAnimationView(false, View.GONE, loginButton, loginButtonProgressBar);
                }
            }
        });
    }

    private void signInEmail(final String email) {
        OneSignal.setEmail(email, new OneSignal.EmailUpdateHandler() {
            @Override
            public void onSuccess() {
                currentUser.setEmail(email);
                oneSignalPrefs.cacheUserEmail(email);
                intentTo.mainActivity();
                Log.d(Tag.DEBUG, Text.EMAIL_LOGIN_SUCCESSFUL);
            }

            @Override
            public void onFailure(OneSignal.EmailUpdateError error) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        animate.toggleAnimationView(false, View.GONE, loginButton, loginButtonProgressBar);
                    }
                });
                Log.e(Tag.ERROR, error.getMessage());
            }
        });
    }


    private void setupContinueToDemoButton() {
        continueToDemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentTo.mainActivity();
            }
        });
    }

}
