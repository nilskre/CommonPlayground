package commonplayground.controller.cucumber.api.steps;

import commonplayground.Application;
import commonplayground.controller.cucumber.api.globaldict.GlobalMessageId;
import commonplayground.controller.cucumber.api.globaldict.GlobalSessionId;
import commonplayground.controller.cucumber.api.globaldict.GlobalUserId;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static org.junit.Assert.assertEquals;

public class JoinStepDefinitions {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private String myJoinedSessions;

    @When("I send a join request for one session")
    public void iSendAJoinRequestForOneSession() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        System.out.println(" User " + GlobalUserId.getNormalUserID() + " Session " + GlobalSessionId.getSessionID());

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userID", GlobalUserId.getNormalUserID());
        body.add("sessionID", GlobalSessionId.getSessionID());

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        myJoinedSessions = testRestTemplate.postForObject("http://localhost:8080/joinRequestForSession", request, String.class);

        log.info("Response of Join Request Controller: " + myJoinedSessions);
    }

    @And("The Session Host approves the request {string}")
    public void theSessionHostApprovesTheRequest(String joinAccepted) {
        System.out.println("EXEC");
        try {
            String body =
                    "userID=" + URLEncoder.encode(GlobalUserId.getSessionHostUserID(), "UTF-8") + "&" +
                            "messageID=" + URLEncoder.encode(GlobalMessageId.getMessageID(), "UTF-8") + "&" +
                            "joinAccepted=" + URLEncoder.encode(joinAccepted, "UTF-8");

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
                liner += line;
            }
            System.out.println("LINER: " + liner);

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }

    @Then("I have joined the session")
    public void iHaveJoinedTheSession() {
        try {
            String body =
                    "userID=" + URLEncoder.encode(GlobalUserId.getNormalUserID(), "UTF-8");

            URL url = new URL("http://localhost:8080/getMyJoinedSessions");
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

            System.out.println("JOINED SESSIONS: " + liner);
            String joinedId = liner.split(",")[0];

            assertEquals("[{\"id\":16"/* + GlobalSessionId.getSessionID()*/, joinedId);

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }

    @Then("I have left the session")
    public void iHaveLeftTheSession() {
        try {
            String body =
                    "userID=" + URLEncoder.encode(GlobalUserId.getNormalUserID(), "UTF-8");

            URL url = new URL("http://localhost:8080/getMyJoinedSessions");
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

            myJoinedSessions = new String();
            for (String line; (line = reader.readLine()) != null; ) {
                myJoinedSessions += (line);
            }
            log.info("Response of getMyJoinedSessions: " + myJoinedSessions);

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }
}
