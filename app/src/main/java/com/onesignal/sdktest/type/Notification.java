package com.onesignal.sdktest.type;

public enum Notification {

    GREETING("Greetings", "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fhuman-greeting.png?alt=media&token=178bd69d-634e-40b3-ac32-b56c88e6cd6a"),
    PROMOTIONS("Promotions", "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fbrightness-percent.png?alt=media&token=6a8b4348-ad51-4e3a-97d0-4deb46b1576e"),
    BREAKING_NEWS("Breaking News", "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fnewspaper.png?alt=media&token=053e419b-14f1-4f0d-a439-bb5b46d1b917"),
    ABANDONED_CART("Abandoned Cart", "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fcart.png?alt=media&token=cf7f4d13-6aa2-4824-9b2f-42e5f33f545b"),
    NEW_POST("New Post", "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fimage.png?alt=media&token=6fb66f31-23de-4c76-a2ff-da40d46ebf15"),
    RE_ENGAGEMENT("Re-Engagement", "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fgesture-tap.png?alt=media&token=045ddcb9-f4e5-457e-8577-baa0e264e227"),
    RATING("Rating", "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fstar.png?alt=media&token=da0987e5-a635-488f-9fba-24a1ee5d704a");


    private final String title;
    private final String iconUrl;

    Notification(String title, String iconUrl) {
        this.title = title;
        this.iconUrl = iconUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getIconUrl() {
        return iconUrl;
    }

}
