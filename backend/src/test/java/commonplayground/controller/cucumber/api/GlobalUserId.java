package commonplayground.controller.cucumber.api;

public class GlobalUserId {
    private static String sessionHostUserID;
    private static String normalUserID;

    public static String getSessionHostUserID() {
        return sessionHostUserID;
    }

    public static void setSessionHostUserID(String sessionHostUserID) {
        GlobalUserId.sessionHostUserID = sessionHostUserID;
    }

    public static String getNormalUserID() {
        return normalUserID;
    }

    public static void setNormalUserID(String normalUserID) {
        GlobalUserId.normalUserID = normalUserID;
    }
}
