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

public class LoginUserStepDefinitions {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private String responseUserIdOrErrorCode = "";

    @When("I login with {string}{string} as test user type {string}")
    public void iLoginWith(String email, String password, String testUserType) {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("email", email);
        body.add("password", password);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        responseUserIdOrErrorCode = testRestTemplate.postForObject("http://localhost:8080/login", request, String.class);

        log.info("Response of Login Controller: " + responseUserIdOrErrorCode);

        if ("sessionHost".equals(testUserType)) {
            GlobalUserId.setSessionHostUserID(responseUserIdOrErrorCode);
        } else if ("normalUser".equals(testUserType)) {
            GlobalUserId.setNormalUserID(responseUserIdOrErrorCode);
        } else {
            GlobalUserId.setAnotherUserID(responseUserIdOrErrorCode);
        }
    }

    @Then("The response is my user ID")
    public void theResponseIsMyUserID() {
        try {
            Integer.parseInt(responseUserIdOrErrorCode);
        } catch (NumberFormatException e) {
            assert false;
        }
    }

    @Then("The login response should be {string}")
    public void theResponseShouldBe(String expectedResponse) {
        System.out.println(" >>> Response: " + responseUserIdOrErrorCode + " expected: " + expectedResponse + " <<<");
        assertEquals(expectedResponse, responseUserIdOrErrorCode);
    }
}
