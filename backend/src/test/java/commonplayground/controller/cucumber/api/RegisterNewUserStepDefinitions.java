package commonplayground.controller.cucumber.api;

import commonplayground.Application;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static org.junit.Assert.assertEquals;

public class RegisterNewUserStepDefinitions {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private StringBuilder response;

    @Given("I register a new account {string}{string}{string}")
    public void iRegisterANewAccount(String username, String password, String email) {
        try {
            String body =
                    "username=" + URLEncoder.encode(username, "UTF-8") + "&" +
                            "password=" + URLEncoder.encode(password, "UTF-8") + "&" +
                            "email=" + URLEncoder.encode(email, "UTF-8");

            URL url = new URL("http://localhost:8080/registerNewUser");
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
                response.append(line);
            }
            System.out.println(response);
            log.info("Response of Register Controller: " + response);

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }

    @Then("The response should be {string}")
    public void theResponseShouldBe(String expectedResponse) {
        log.info(" >>> Response: " + response + " expected: " + expectedResponse + " <<<");
        assertEquals(expectedResponse, response.toString());
    }
}
