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
            JSONObject notificationContent = new JSONObject("{'include_player_ids': ['" + userId + "']," +
                    "'android_sound': 'nil'," +
                    "'small_icon': '" + notification.getSmallIconRes() + "'," +
                    "'large_icon': '" + notification.getLargeRedIconUrl() + "'," +
                    "'android_led_color': 'FFE9444E'," +
                    "'android_accent_color': 'FFE9444E'," +
                    "'headings': {'en': '" + notification.getTitle() + "'}," +
                    "'contents': {'en': '" + notification.getRandomMessage() + "'}," +
                    "'android_group': '" + notification.getTitle() + "'}");
            OneSignal.postNotification(notificationContent, new OneSignal.PostNotificationResponseHandler() {
                @Override
                public void onSuccess(JSONObject response) {
                    System.out.println(response.toString());
                }

                @Override
                public void onFailure(JSONObject response) {
                    System.out.println(response.toString());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
