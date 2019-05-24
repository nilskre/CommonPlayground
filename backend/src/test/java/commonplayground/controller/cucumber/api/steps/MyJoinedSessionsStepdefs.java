package commonplayground.controller.cucumber.api.steps;

import commonplayground.Application;
import commonplayground.controller.cucumber.api.globaldict.GlobalUserId;
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

import static org.junit.Assert.assertEquals;

public class MyJoinedSessionsStepdefs {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private String joinedSessions = "";

    @When("I request my joined sessions")
    public void iRequestMyJoinedSessions() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userID", GlobalUserId.getSessionHostUserID());

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        joinedSessions = testRestTemplate.postForObject("http://localhost:8080/getMyJoinedSessions", request, String.class);
        log.info("Response of MyJoinedSessions Controller: " + joinedSessions);
    }

    @Then("^There are no joined sessions$")
    public void iRequestMyHostedSessions() {
        assertEquals("[]", joinedSessions);
    }
}
