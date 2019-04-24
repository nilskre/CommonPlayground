package gradle.cucumber.api;

import commonplayground.Application;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.boot.SpringApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static io.restassured.RestAssured.get;


public class postSessionStepDefinitions {

    @Given("The backend is active")
    public void theBackendIsActive() {
        try {
            SpringApplication.run(Application.class);
        } catch (Exception e) {
            assert false;
        }
    }

    @And("^I post a new Session$")
    public void iSendARequestToTheBackend() {
        try {
            String body =
                    "name=" + URLEncoder.encode("NAMETEST", "UTF-8") + "&" +
                            "description=" + URLEncoder.encode("DESCIPTIONTEST", "UTF-8") + "&" +
                            "game=" + URLEncoder.encode("GAMETEST", "UTF-8") + "&" +
                            "place=" + URLEncoder.encode("PLACETEST", "UTF-8") + "&" +
                            "date=" + URLEncoder.encode("12-12-2020", "UTF-8") + "&" +
                            "numberOfPlayers=" + URLEncoder.encode(Integer.toString(2), "UTF-8");

            URL url = new URL("http://localhost:8080/postNewSession");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(body.length()));

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();


            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            for (String line; (line = reader.readLine()) != null; ) {
                System.out.println(line);
            }

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }

    @Then("^There should be my PostedSession$")
    public void thereShouldBeSessions() {
        assert get("/getSessionList").jsonPath().getList("title").contains("NAMETEST");
    }
}
