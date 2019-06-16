package com.onesignal.sdktest.notification;

import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationPayload;
import com.onesignal.OSNotificationReceivedResult;
import com.onesignal.sdktest.R;

public class NotificationExtenderServiceTest extends NotificationExtenderService {

   @Override
   protected boolean onNotificationProcessing(OSNotificationReceivedResult notification) {
//      DebuggingHelper.printObject(notification);
      if (notification.payload.actionButtons != null) {
         for(OSNotificationPayload.ActionButton button : notification.payload.actionButtons) {
            System.out.println("button:" + button.toString());
//            DebuggingHelper.printObject(button);
         }
      }

      OverrideSettings overrideSettings = new NotificationExtenderService.OverrideSettings();

      overrideSettings.extender = new NotificationCompat.Extender() {
         @Override
         public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
            return builder.setColor(getResources().getColor(R.color.colorPrimary));
         }
      };

      displayNotification(overrideSettings);

      return true;
   }
}