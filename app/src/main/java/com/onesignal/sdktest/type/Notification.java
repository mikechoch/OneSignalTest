package com.onesignal.sdktest.type;

import com.onesignal.sdktest.notification.NotificationMessage;

import java.util.Random;

public enum Notification {

    GENERAL("General",
            NotificationMessage.GENERAL_MESSAGE,
            "ic_bell_white_24dp",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fbell.png?alt=media&token=73c2bdd9-355f-42bb-80d7-aead737a9dbc",
            "https://maps.googleapis.com/maps/api/staticmap?center=3708+Kenwood+Avenue,San+Mateo,CA&zoom=14.5&size=600x600&maptype=roadmap&key=AIzaSyCpslt6X1qbJX0FRrnEi_9ESiYI55uJLl4",
//            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fbell_red.png?alt=media&token=c80c4e76-1fd7-4912-93f4-f1aee1d98b20",
            true),

    GREETING("Greetings",
            NotificationMessage.GREETING_MESSAGE,
            "ic_human_greeting_white_24dp",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fhuman-greeting.png?alt=media&token=178bd69d-634e-40b3-ac32-b56c88e6cd6a",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fhuman-greeting-red.png?alt=media&token=cb9f3418-db61-443c-955a-57e664d30271",
            true),

    PROMOTIONS("Promotions",
            NotificationMessage.PROMOTION_MESSAGE,
            "ic_brightness_percent_white_24dp",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fbrightness-percent.png?alt=media&token=6a8b4348-ad51-4e3a-97d0-4deb46b1576e",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fbrightness-percent-red.png?alt=media&token=9e43c45e-8bcc-413e-8a42-612020c406ba",
            true),

    BREAKING_NEWS("Breaking News",
            NotificationMessage.BREAKING_NEWS_MESSAGE,
            "ic_newspaper_white_24dp",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fnewspaper.png?alt=media&token=053e419b-14f1-4f0d-a439-bb5b46d1b917",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fnewspaper-red.png?alt=media&token=256e966e-8e92-4995-b273-df60508460b0",
            true),

    ABANDONED_CART("Abandoned Cart",
            NotificationMessage.ABANDONED_CART_MESSAGE,
            "ic_cart_white_24dp",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fcart.png?alt=media&token=cf7f4d13-6aa2-4824-9b2f-42e5f33f545b",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fcart-red.png?alt=media&token=3e9ca206-540c-4275-8f21-1840e9cba930",
            true),

    NEW_POST("New Post",
            NotificationMessage.NEW_POST_MESSAGE,
            "ic_image_white_24dp",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fimage.png?alt=media&token=6fb66f31-23de-4c76-a2ff-da40d46ebf15",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fimage-red.png?alt=media&token=3f44fd3d-27a5-4d05-9544-423edf2f6284",
            true),

    RE_ENGAGEMENT("Re-Engagement",
            NotificationMessage.RE_ENGAGEMENT_MESSAGE,
            "ic_gesture_tap_white_24dp",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fgesture-tap.png?alt=media&token=045ddcb9-f4e5-457e-8577-baa0e264e227",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fgesture-tap-red.png?alt=media&token=8ea7f6db-18e4-4fdd-aabf-ac97f04522fd",
            true),

    RATING("Rating",
            NotificationMessage.RATING_MESSAGE,
            "ic_star_white_24dp",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fstar.png?alt=media&token=da0987e5-a635-488f-9fba-24a1ee5d704a",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fstar-red.png?alt=media&token=e18e99ce-96ad-4ee5-b0b9-40c7f90613d1",
            true),

    LOCATION("Location",
            NotificationMessage.LOCATION_MESSAGE,
            "ic_map_marker_white_24dp",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fmap-marker.png?alt=media&token=92162fce-f206-40a9-b369-6459a2b1a41b",
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fmap-marker-red.png?alt=media&token=42631ed8-053b-4c28-a193-4d144001a474",
            true);

    private final String title;
    private final String[] messages;
    private final String smallIconRes;
    private final String largeIconUrl;
    private final String largeRedIconUrl;
    private final boolean shouldShow;

    Notification(String title, String[] messages, String smallIconRes, String largeIconUrl, String largeRedIconUrl, boolean shouldShow) {
        this.title = title;
        this.messages = messages;
        this.smallIconRes = smallIconRes;
        this.largeIconUrl = largeIconUrl;
        this.largeRedIconUrl = largeRedIconUrl;
        this.shouldShow = shouldShow;
    }

    public String getTitle() {
        return title;
    }

    public String getRandomMessage() {
        Random random = new Random();
        int pos = random.nextInt(2);
        return messages[pos];
    }

    public String getSmallIconRes() {
        return smallIconRes;
    }

    public String getLargeIconUrl() {
        return largeIconUrl;
    }

    public String getLargeRedIconUrl() {
        return largeRedIconUrl;
    }

    public boolean shouldShow() {
        return shouldShow;
    }
}
