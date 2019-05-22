package commonplayground.controller.cucumber.api.steps;

import commonplayground.controller.cucumber.api.globaldict.GlobalUserId;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MyHostedSessionsStepDefs {
    private StringBuilder response;

    @When("I request my hosted sessions")
    public void iRequestMyHostedSessions() {
        System.out.println("\n\n HOST ID: " + GlobalUserId.getSessionHostUserID());
        try {
            String body =
                    "userID=" + URLEncoder.encode(GlobalUserId.getSessionHostUserID(), "UTF-8");

            URL url = new URL("http://localhost:8080/getMyHostedSessions");
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

            response = new StringBuilder();

            for (String line; (line = reader.readLine()) != null; ) {
                System.out.println(line);
                response.append(line);
            }
            System.out.println("\n RESPONSE");
            System.out.println(response);

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }

    @Then("^There should be my hosted sessions$")
    public void thereShouldBeMyPostedSessionWithCorrectData() {
        //tbd
    }
}
