package commonplayground.controller.cucumber.api;

import commonplayground.model.Session;
import commonplayground.model.TestData;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static io.restassured.RestAssured.get;

public class MyHostedSessionsStepDefs {
    private TestData testData = new TestData();
    private String response = "";

    @When("I request my hosted sessions")
    public void iRequestMyHostedSessions() {
        try {
            String body =
                    "userID=" + URLEncoder.encode(GlobalUserId.getSessionHostUserID(), "UTF-8");

            URL url = new URL("http://localhost:8080/getMyHostedSessions");
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
                System.out.println(line);
                response = line;
            }
            System.out.println("\n RESPONSE");
            System.out.println(response);

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }

    @Then("^There should be my hosted sessions$")
    public void thereShouldBeMyPostedSessionWithCorrectData() {
        for (Session testSession : testData.getTestSessions()) {
            assert get("/getMyHostedSessions").jsonPath().getList("title").contains(testSession.getTitle());
            assert get("/getMyHostedSessions").jsonPath().getList("description").contains(testSession.getDescription());
            assert get("/getMyHostedSessions").jsonPath().getList("game").contains(testSession.getGame());
            assert get("/getMyHostedSessions").jsonPath().getList("place").contains(testSession.getPlace());
            assert get("/getMyHostedSessions").jsonPath().getList("date").contains(testSession.getDate());
            assert get("/getMyHostedSessions").jsonPath().getList("time").contains(testSession.getTime());
            assert get("/getMyHostedSessions").jsonPath().getList("numberOfPlayers").contains(testSession.getNumberOfPlayers());
            assert get("/getMyHostedSessions").jsonPath().getList("idOfHost").contains(testSession.getIdOfHost());
            assert get("/getMyHostedSessions").jsonPath().getList("genre").contains(testSession.getGenre());
            assert get("/getMyHostedSessions").jsonPath().getList("isOnline").contains(testSession.getIsOnline());
        }
    }
}
