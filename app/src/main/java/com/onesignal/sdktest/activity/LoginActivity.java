package com.onesignal.sdktest.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.onesignal.sdktest.R;
import com.onesignal.sdktest.model.ActivityViewModel;
import com.onesignal.sdktest.model.LoginActivityViewModel;

public class LoginActivity extends AppCompatActivity{

    private ActivityViewModel viewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);

        viewModel = new LoginActivityViewModel()
                .onActivityCreated(this)
                .setupInterfaceElements();


    }
}
