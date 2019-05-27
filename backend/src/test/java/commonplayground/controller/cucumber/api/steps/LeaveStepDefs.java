package commonplayground.controller.cucumber.api.steps;

import commonplayground.Application;
import commonplayground.controller.cucumber.api.globaldict.GlobalSessionId;
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

public class LeaveStepDefs {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private String leaveSessionResponse;

    @When("{string} sends a leave request for one session")
    public void iSendALeaveRequestForOneSession(String testUserType) {
        String userID;
        if (testUserType.equals("sessionHost") && GlobalUserId.getSessionHostUserID() != null) {
            userID = GlobalUserId.getSessionHostUserID();
        } else if (testUserType.equals("normalUser") && GlobalUserId.getSessionHostUserID() != null) {
            userID = GlobalUserId.getNormalUserID();
        } else {
            userID = GlobalUserId.getAnotherUserID();
        }
        System.out.println("User ID (Leave) " + userID + " Session to leave: " + GlobalSessionId.getSessionID());

        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userID", userID);
        body.add("sessionID", GlobalSessionId.getSessionID());

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        leaveSessionResponse = testRestTemplate.postForObject("http://localhost:8080/leaveSession", request, String.class);

        log.info("Response of Leave Session Controller: (0 if all ok) " + leaveSessionResponse);
    }

    @Then("The return code should be {int}")
    public void theReturnCodeShouldBe(int expectedResult) {
        System.out.println("DAS IST erwartet" + expectedResult + " real: " + leaveSessionResponse);
        assertEquals(expectedResult, Integer.parseInt(leaveSessionResponse));
    }

    @When("I unset global session id var")
    public void iUnsetGlobalSessionIdVar() {
        GlobalSessionId.setSessionID(null);
    }
}
