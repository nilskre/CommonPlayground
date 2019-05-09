package commonplayground.controller.cucumber.api;

public class GlobalUserId {
    private static String hostUserID;
    private static String normalUserID;

    public static String getHostUserID() {
        return hostUserID;
    }

    public static void setHostUserID(String hostUserID) {
        GlobalUserId.hostUserID = hostUserID;
    }

    public static String getNormalUserID() {
        return normalUserID;
    }

    public static void setNormalUserID(String normalUserID) {
        GlobalUserId.normalUserID = normalUserID;
    }
}
