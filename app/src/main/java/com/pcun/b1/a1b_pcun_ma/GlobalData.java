package com.pcun.b1.a1b_pcun_ma;

import android.app.Application;

public class GlobalData extends Application {
    private String sessionToken = "";
    private boolean isSessionVerified = false;
    private int currentUser = -1;
    private String currentUsername = "";

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }


    public boolean isSessionVerified() {
        return isSessionVerified;
    }

    public void setSessionVerified(boolean sessionVerified) {
        isSessionVerified = sessionVerified;
    }

    public int getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(int currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }
}
