package commonplayground.controller.cucumber.api;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class JoinStepDefinitions {

    @When("I send a join request for one session")
    public void iSendAJoinRequestForOneSession() {
        try {
            String body =
                    "userID=" + URLEncoder.encode(GlobalUserId.getNormalUserID(), "UTF-8") + "&" +
                            "sessionID=" + URLEncoder.encode(GlobalSessionId.getSessionID(), "UTF-8");

            URL url = new URL("http://localhost:8080/joinRequestForSession");
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

            String sessionID = "-42";
            for (String line; (line = reader.readLine()) != null; ) {
                sessionID = line;
            }
            GlobalSessionId.setSessionID(sessionID);

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }

    @And("The Session Host approves the request")
    public void theSessionHostApprovesTheRequest() {
        try {
            String body =
                    "userID=" + URLEncoder.encode(GlobalUserId.getNormalUserID(), "UTF-8") + "&" +
                            "messageID=" + URLEncoder.encode(GlobalSessionId.getSessionID(), "UTF-8") + "&" +
                            "joinAccepted=" + URLEncoder.encode(GlobalSessionId.getSessionID(), "UTF-8");

            URL url = new URL("http://localhost:8080/joinResponse");
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

            String liner = "-42";
            for (String line; (line = reader.readLine()) != null; ) {
                liner = line;
            }

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }

    @Then("I have joined the session")
    public void iHaveJoinedTheSession() {
    }
}
