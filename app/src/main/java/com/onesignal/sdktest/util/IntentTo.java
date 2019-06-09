package com.onesignal.sdktest.util;

import android.app.Activity;
import android.content.ComponentName;
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
        ComponentName componentName = mainActivityIntent.getComponent();
        mainActivityIntent = Intent.makeRestartActivityTask(componentName);
        context.startActivity(mainActivityIntent);
        ((Activity) context).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void resetApplication() {
        Intent resetApplicationIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (resetApplicationIntent != null) {
            resetApplicationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        context.startActivity(resetApplicationIntent);
        ((Activity) context).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
