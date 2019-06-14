package com.onesignal.sdktest.type;

public enum Notification {

    GENERAL("General",
            new String[]{""},
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fbell.png?alt=media&token=d1fc4ade-1da4-4337-a48d-d54748b6bb63",
            true),

    GREETING("Greetings",
            new String[]{""},
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fhuman-greeting.png?alt=media&token=178bd69d-634e-40b3-ac32-b56c88e6cd6a",
            true),

    PROMOTIONS("Promotions",
            new String[]{""},
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fbrightness-percent.png?alt=media&token=6a8b4348-ad51-4e3a-97d0-4deb46b1576e",
            true),

    BREAKING_NEWS("Breaking News",
            new String[]{""},
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fnewspaper.png?alt=media&token=053e419b-14f1-4f0d-a439-bb5b46d1b917",
            true),

    ABANDONED_CART("Abandoned Cart",
            new String[]{""},
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fcart.png?alt=media&token=cf7f4d13-6aa2-4824-9b2f-42e5f33f545b",
            true),

    NEW_POST("New Post",
            new String[]{""},
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fimage.png?alt=media&token=6fb66f31-23de-4c76-a2ff-da40d46ebf15",
            true),

    RE_ENGAGEMENT("Re-Engagement",
            new String[]{""},
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fgesture-tap.png?alt=media&token=045ddcb9-f4e5-457e-8577-baa0e264e227",
            true),

    RATING("Rating",
            new String[]{""},
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fstar.png?alt=media&token=da0987e5-a635-488f-9fba-24a1ee5d704a",
            true),

    LOCATION("Location",
            new String[]{""},
            "https://firebasestorage.googleapis.com/v0/b/onesignaltest-e7802.appspot.com/o/NOTIFICATION_ICON%2Fmap-marker.png?alt=media&token=92162fce-f206-40a9-b369-6459a2b1a41b",
            true);

    private final String title;
    private final String[] messages;
    private final String iconUrl;
    private final boolean shouldShow;

    Notification(String title, String[] messages, String iconUrl, boolean shouldShow) {
        this.title = title;
        this.messages = messages;
        this.iconUrl = iconUrl;
        this.shouldShow = shouldShow;
    }

    public String getTitle() {
        return title;
    }

    public String[] getRandomMessage() {
        return messages;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public boolean isShouldShow() {
        return shouldShow;
    }
}
