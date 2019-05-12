package commonplayground.controller.cucumber.api;

import commonplayground.Application;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static org.junit.Assert.assertEquals;

public class LoginUserStepDefinitions /*extends CucumberRuntime */{
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private String responseUserIdOrErrorCode = "";

    /*public LoginUserStepDefinitions() {
        super();
    }

    @Before
    public void testSetUpCu() {
        super.testSetUpCu();
        System.out.println("EXECUTE CU");
        //super.gedings();
        //test();
    }

    @After
    public void tearDown() {
        super.tearDown();
        System.out.println("EXIT");
    }*/

    @When("I login with {string}{string} as test user type {string}")
    public void iLoginWith(String email, String password, String testUserType) {
        try {
            String body =
                    "email=" + URLEncoder.encode(email, "UTF-8") + "&" +
                            "password=" + URLEncoder.encode(password, "UTF-8");

            URL url = new URL("http://localhost:8080/login");
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

            for (String line; (line = reader.readLine()) != null; ) {
                responseUserIdOrErrorCode = line;
            }

            log.info("Response of Register Controller: " + responseUserIdOrErrorCode);

            if (testUserType.equals("sessionHost")) {
                GlobalUserId.setSessionHostUserID(responseUserIdOrErrorCode);
            } else if (testUserType.equals("normalUser")) {
                GlobalUserId.setNormalUserID(responseUserIdOrErrorCode);
            } else {
                //TODO another user
                //GlobalUserId.setNormalUserID(responseUserIdOrErrorCode);
            }

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
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
        assertEquals(expectedResponse, responseUserIdOrErrorCode.toString());
    }
}
