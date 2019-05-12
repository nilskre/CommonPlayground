package commonplayground.controller.cucumber.api.globaldict;

public class GlobalUserId {
    private static String sessionHostUserID;
    private static String normalUserID;
    private static String anotherUserID;

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

    public static String getAnotherUserID() {
        return anotherUserID;
    }

    public static void setAnotherUserID(String anotherUserID) {
        GlobalUserId.anotherUserID = anotherUserID;
    }
}
