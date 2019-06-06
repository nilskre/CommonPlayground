package commonplayground.controller.cucumber.api.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import commonplayground.Application;
import commonplayground.controller.cucumber.api.globaldict.GlobalMessageId;
import commonplayground.controller.cucumber.api.globaldict.GlobalSessionId;
import commonplayground.controller.cucumber.api.globaldict.GlobalUserId;
import commonplayground.model.Session;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JoinStepDefinitions {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private String response;

    @When("I send a join request for one session")
    public void iSendAJoinRequestForOneSession() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        System.out.println(" User (join request " + GlobalUserId.getNormalUserID() + " Session " + GlobalSessionId.getSessionID());

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userID", GlobalUserId.getNormalUserID());
        body.add("sessionID", GlobalSessionId.getSessionID());

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        response = testRestTemplate.postForObject("http://localhost:8080/joinRequestForSession", request, String.class);

        log.info("Response of Join Request Controller: " + response);
    }

    @And("The Session Host approves the request {string}")
    public void theSessionHostApprovesTheRequest(String joinAccepted) {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userID", GlobalUserId.getSessionHostUserID());
        body.add("messageID", GlobalMessageId.getMessageID());
        body.add("userIDToJoin", GlobalUserId.getNormalUserID());
        body.add("joinAccepted", joinAccepted);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        response = testRestTemplate.postForObject("http://localhost:8080/joinResponse", request, String.class);

        log.info("Response of Host approves Join Request Controller: (should be void)" + response);

        System.out.println("EXEC");
    }

    @Then("I have joined the session")
    public void iHaveJoinedTheSession() {

        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userID", GlobalUserId.getNormalUserID());

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> tmp = testRestTemplate.postForEntity("http://localhost:8080/getMyJoinedSessions", request, String.class);

        String myJoinedSessionsString = tmp.getBody();
        log.info("My joined sessions as String: " + myJoinedSessionsString);

        List<Session> myJoinedSessionsList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            // JSON string to Java object
            Session[] myMessageArray = mapper.readValue(myJoinedSessionsString, Session[].class);

            myJoinedSessionsList = Arrays.asList(myMessageArray);
            String prettyPrint = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(myMessageArray);
            System.out.println("JSON MyJoinedSessions \n" + prettyPrint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(myJoinedSessionsList);
        String joinedId = String.valueOf(myJoinedSessionsList.get(0).getId());
        assertEquals(GlobalSessionId.getSessionID(), joinedId);
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

            response = "";
            for (String line; (line = reader.readLine()) != null; ) {
                response += (line);
            }
            log.info("Response of getMyJoinedSessions: " + response);

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }
}
