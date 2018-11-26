package com.wordpress.commonplayground;

public class UserCred {
    private static long currentUserID =0;

    public static long getCurrentUserID() {
        return currentUserID;
    }

    public void setCurrentUserID(long currentUserID) {
        this.currentUserID = currentUserID;
    }

}
