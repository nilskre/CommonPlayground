package commonplayground.controller.cucumber.api.globaldict;

public class GlobalSessionId {
    private static String sessionID;

    public static String getSessionID() {
        return sessionID;
    }

    public static void setSessionID(String userID) {
        GlobalSessionId.sessionID = userID;
    }
}
