package com.onesignal.sdktest.type;

public enum Notification {

    GREETING("Greetings", "https://playground.pushowl.com/static/img/greetings.7d7fc15.svg"),
    PRMOTIONS("Promotions", "https://i.postimg.cc/htHN3nP1/download.png"),
    BREAKING_NEWS("Breaking News", "https://i.postimg.cc/kG0xPTVz/breaking-News.png"),
    ADANDONED_CART("Abandoned Cart", "https://i.postimg.cc/VLtqWPp8/abandoned-Cart.png"),
    NEW_POST("New Post", "https://i.postimg.cc/Df8Rr5hT/newPost.png"),
    REENGAGEMENT("Re-Engagement", "https://i.postimg.cc/FKr0vdRr/re-engagement.png");

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
