package com.onesignal.sdktest.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.onesignal.sdktest.R;
import com.onesignal.sdktest.activity.MainActivity;

public class IntentTo {

    private Context context;


    public IntentTo(Context context) {
        this.context = context;
    }

    public void mainActivity() {
        Intent mainActivityIntent = new Intent(context, MainActivity.class);

        context.startActivity(mainActivityIntent);
        ((Activity) context).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
