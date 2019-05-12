package commonplayground.controller.cucumber.api.steps;

import commonplayground.Application;
import commonplayground.controller.cucumber.api.globaldict.GlobalSessionId;
import commonplayground.controller.cucumber.api.globaldict.GlobalUserId;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LeaveStepDefs /*extends CucumberRuntime*/ {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private StringBuilder leaveSessionResponse;

    @When("{string} sends a leave request for one session")
    public void iSendALeaveRequestForOneSession(String testUserType) {
        String hostID = "-20";
        if (testUserType.equals("sessionHost") && GlobalUserId.getSessionHostUserID() != null) {
            hostID = GlobalUserId.getSessionHostUserID();
        } else if (testUserType.equals("normalUser") && GlobalUserId.getSessionHostUserID() != null) {
            hostID = GlobalUserId.getNormalUserID();
        } else {
            hostID = GlobalUserId.getAnotherUserID();
        }
        System.out.println("HOSTID " + GlobalUserId.getSessionHostUserID());
        try {
            String body =
                    "userID=" + URLEncoder.encode(hostID, "UTF-8") + "&" +
                            "sessionID=" + URLEncoder.encode(GlobalSessionId.getSessionID(), "UTF-8");

            URL url = new URL("http://localhost:8080/leaveSession");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(body.length()));

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            leaveSessionResponse = new StringBuilder();
            for (String line; (line = reader.readLine()) != null; ) {
                leaveSessionResponse.append(line);
            }
            log.info("Response of Leaving Controller: " + leaveSessionResponse);
            System.out.println("Response of Leaving Controller: " + leaveSessionResponse);

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }

    @Then("The return code should be {int}")
    public void theReturnCodeShouldBe(int expectedResult) {
//        System.out.println("DAS IST erwartet" + expectedResult + " real: " + leaveSessionResponse.toString());
        assert expectedResult == Integer.parseInt(leaveSessionResponse.toString());
    }
}
