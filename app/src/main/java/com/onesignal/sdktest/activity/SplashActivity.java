package com.onesignal.sdktest.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.onesignal.OneSignal;
import com.onesignal.sdktest.R;
import com.onesignal.sdktest.model.ActivityViewModel;
import com.onesignal.sdktest.model.SplashActivityViewModel;
import com.onesignal.sdktest.util.IntentTo;

public class SplashActivity extends AppCompatActivity {

    private IntentTo intentTo;

    private ActivityViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity_layout);

        intentTo = new IntentTo(this);

        viewModel = new SplashActivityViewModel()
                .onActivityCreated(this)
                .setupInterfaceElements();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                OneSignal.EmailUpdateHandler callback = new OneSignal.EmailUpdateHandler() {
                    @Override
                    public void onSuccess() {
                        intentTo.mainActivity();
                    }

                    @Override
                    public void onFailure(OneSignal.EmailUpdateError error) {
                        intentTo.loginActivity();
                    }
                };

                if (!((SplashActivityViewModel) viewModel).attemptSignIn(callback)) {
                    intentTo.loginActivity();
                }
            }
        }, 1000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}
