package commonplayground.controller.cucumber.api.steps;

import commonplayground.Application;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.assertEquals;

public class RegisterNewUserStepDefinitions {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private String response;

    public RegisterNewUserStepDefinitions() {
    }

    @Given("I register a new account {string}{string}{string}")
    public void iRegisterANewAccount(String username, String password, String email) {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("username", username);
        body.add("password", password);
        body.add("email", email);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        response = testRestTemplate.postForObject("http://localhost:8080/registerNewUser", request, String.class);
        log.info("Response of Register Controller: " + response);
    }

    @Then("The response should be {string}")
    public void theResponseShouldBe(String expectedResponse) {
        log.info(" >>> Response: " + response + " expected: " + expectedResponse + " <<<");
        assertEquals(expectedResponse, response);
    }
}
