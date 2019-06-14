package com.onesignal.sdktest.user;

import android.util.Log;
import android.view.View;

import com.onesignal.OneSignal;
import com.onesignal.sdktest.EmailUpdateCallback;
import com.onesignal.sdktest.constant.Tag;
import com.onesignal.sdktest.constant.Text;

public class CurrentUser {

    private static CurrentUser currentUser;

    public String getEmail() {
        return OneSignal
                .getPermissionSubscriptionState()
                .getEmailSubscriptionStatus()
                .getEmailAddress();
    }

    public void signIn(String email, final EmailUpdateCallback callback) {
        OneSignal.setEmail(email, new OneSignal.EmailUpdateHandler() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }

            @Override
            public void onFailure(OneSignal.EmailUpdateError error) {
                String errorMsg = error.getType() + ": " + error.getMessage();
                Log.e(Tag.ERROR, errorMsg);

                callback.onFailure();
            }
        });
    }

    public void logout(final EmailUpdateCallback callback) {
        OneSignal.logoutEmail();
        callback.onSuccess();
    }

    public boolean isSignedIn() {
        return OneSignal
                .getPermissionSubscriptionState()
                .getEmailSubscriptionStatus()
                .getEmailAddress() != null;
    }

    public static CurrentUser getInstance() {
        if (currentUser == null) {
            currentUser = new CurrentUser();
        }
        return currentUser;
    }
}
