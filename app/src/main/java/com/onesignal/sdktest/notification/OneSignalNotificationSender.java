package com.onesignal.sdktest.notification;

import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;
import com.onesignal.sdktest.type.Notification;

import org.json.JSONException;
import org.json.JSONObject;

public class OneSignalNotificationSender {

    public static void sendDeviceNotification(Notification notification) {
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

}
