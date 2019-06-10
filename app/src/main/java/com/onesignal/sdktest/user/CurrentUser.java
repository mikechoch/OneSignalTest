package com.onesignal.sdktest.user;

public class CurrentUser {

    private static CurrentUser currentUser;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSignedIn() {
        return email != null;
    }

    public static CurrentUser getInstance() {
        if (currentUser == null) {
            currentUser = new CurrentUser();
        }
        return currentUser;
    }

}
