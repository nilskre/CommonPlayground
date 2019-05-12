package commonplayground.controller.cucumber.api;

import commonplayground.Application;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Ignore
/*@WebAppConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = Application.class)
*/
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@ContextConfiguration(classes = Application.class)
public class RegisterNewUserStepDefinitions extends CucumberRuntime {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    //private String url = DEFAULT_URL + "/registerNewUser";
    private StringBuilder response;

    public RegisterNewUserStepDefinitions() {
        super();
    }

    /*@BeforeClass
    public static void setUp() {
        //super.gedings();
        //test();
    }*/

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
    }


    @Test
    public void testMethod(){
        System.out.println("RAN");
        //SpringApplication.run(Application.class);
        assert true;
    }

    /*@BeforeClass
    void doSpecificInitialization() {
        super.gedings();
    }*/

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Then("The response should be {string}")
    public void theResponseShouldBe(String expectedResponse) {
        System.out.println(" >>> Response: " + response + " expected: " + expectedResponse + " <<<");
        assert (response.toString().equals(expectedResponse));
    }


    @Given("I register a new account {string}{string}{string}")
    public void iRegisterANewAccount(String username, String password, String email) {

        /*String body = this.restTemplate.getForObject("http://localhost:8080/registerNewUser", String.class);
        Assert.assertTrue((body).equals("Hello World"));*/

        /*testRestTemplate = new TestRestTemplate();

        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("username", username);
        parts.add("password", password);
        parts.add("email", email);

        //response = restTemplate.postForObject("http://localhost:8080/registerNewUser", parts, String.class);
        //ResponseEntity<String> response = testRestTemplate.postForEntity(
        //        "http://localhost:8080/registerNewUser",
        //        String.class);
        String response = testRestTemplate.postForObject("/registerNewUser", parts, String.class);

        if (response.equals("0")) {
            System.out.println(response);
        } else {
            System.out.println("ERRRRRR");
        }
        System.out.println("RESPONSE: " + response);*/

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
}
