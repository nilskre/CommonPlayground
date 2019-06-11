package commonplayground.controller.cucumber.api.steps;

import commonplayground.Application;
import commonplayground.controller.cucumber.api.globaldict.GlobalSessionId;
import commonplayground.controller.cucumber.api.globaldict.GlobalUserId;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static org.junit.Assert.assertEquals;

public class MyPendingSessionsStepDefinitions {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @When("I request my pending sessions as test user type {string}")
    public void iSendAJoinRequestForOneSession(String testUserType) {
        String hostID;
        if (testUserType.equals("sessionHost") && GlobalUserId.getSessionHostUserID() != null) {
            hostID = GlobalUserId.getSessionHostUserID();
        } else if (testUserType.equals("normalUser") && GlobalUserId.getSessionHostUserID() != null) {
            hostID = GlobalUserId.getNormalUserID();
        } else {
            hostID = GlobalUserId.getAnotherUserID();
        }
        try {
            String body =
                    "userID=" + URLEncoder.encode(hostID, "UTF-8") + "&" +
                            "sessionID=" + URLEncoder.encode(GlobalSessionId.getSessionID(), "UTF-8");

            URL url = new URL("http://localhost:8080/getMyPendingSessions");
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

            StringBuilder myPendingSessions = new StringBuilder();
            for (String line; (line = reader.readLine()) != null; ) {
                myPendingSessions.append(line);
            }
            log.info("My pending sessions: " + myPendingSessions);

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }

    @And("There is the session I want to join")
    public void thereIsTheSessionIWantToJoin() {
        //tbd
    }

    @Then("Corrupt request sent and internal server error is returned pending")
    public void corruptRequestSentAndInternalServerErrorIsReturnedPending() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userID", -2);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("http://localhost:8080/getMyPendingSessions", request, String.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
