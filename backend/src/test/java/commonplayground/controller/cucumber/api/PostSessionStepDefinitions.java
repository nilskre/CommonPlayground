package commonplayground.controller.cucumber.api;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static io.restassured.RestAssured.get;


public class PostSessionStepDefinitions {

    @When("^I post a new Session with title ([^\"]*) description ([^\"]*) game ([^\"]*) place ([^\"]*) date ([^\"]*) time ([^\"]*) numberOfPlayers ([^\"]*) idOfHost ([^\"]*) genre ([^\"]*) isOnline ([^\"]*)$")
    public void iPostANewSessionWithTitleTitleDescriptionDescriptionGameGamePlacePlaceDateDateTimeTimeNumberOfPlayersNumberOfPlayersIdOfHostIdOfHostGenreGenreIsOnlineIsOnline(String testTitle, String testDescription, String testGame, String testPlace, String testDate, String testTime, String testNumberOfPlayers, String testIdOfHost, String testGenre, String testIsOnline) {
        try {
            String body =
                    "title=" + URLEncoder.encode(testTitle, "UTF-8") + "&" +
                            "description=" + URLEncoder.encode(testDescription, "UTF-8") + "&" +
                            "game=" + URLEncoder.encode(testGame, "UTF-8") + "&" +
                            "place=" + URLEncoder.encode(testPlace, "UTF-8") + "&" +
                            "date=" + URLEncoder.encode(testDate, "UTF-8") + "&" +
                            "time=" + URLEncoder.encode(testTime, "UTF-8") + "&" +
                            "numberOfPlayers=" + URLEncoder.encode(testNumberOfPlayers, "UTF-8") + "&" +
                            "idOfHost=" + URLEncoder.encode(testIdOfHost, "UTF-8") + "&" +
                            "genre=" + URLEncoder.encode(testGenre, "UTF-8") + "&" +
                            "isOnline=" + URLEncoder.encode(testIsOnline, "UTF-8");

            URL url = new URL("http://localhost:8080/postNewSession");
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
            }

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }

    @Then("^There should be my PostedSession with title ([^\"]*) description ([^\"]*) game ([^\"]*) place ([^\"]*) date ([^\"]*) time ([^\"]*) numberOfPlayers ([^\"]*) idOfHost ([^\"]*) genre ([^\"]*) isOnline ([^\"]*)$")
    public void thereShouldBeMyPostedSessionWithTitleTitleDescriptionDescriptionGameGamePlacePlaceDateDateTimeTimeNumberOfPlayersNumberOfPlayersIdOfHostIdOfHostGenreGenreIsOnlineIsOnline(String testTitle, String testDescription, String testGame, String testPlace, String testDate, String testTime, String testNumberOfPlayers, String testIdOfHost, String testGenre, String testIsOnline) {
        assert get("/getSessionList").jsonPath().getList("title").contains(testTitle);
        assert get("/getSessionList").jsonPath().getList("description").contains(testDescription);
        assert get("/getSessionList").jsonPath().getList("game").contains(testGame);
        assert get("/getSessionList").jsonPath().getList("place").contains(testPlace);
        assert get("/getSessionList").jsonPath().getList("date").contains(testDate);
        assert get("/getSessionList").jsonPath().getList("time").contains(testTime);
        assert get("/getSessionList").jsonPath().getList("numberOfPlayers").contains(testNumberOfPlayers);
        assert get("/getSessionList").jsonPath().getList("idOfHost").contains(testIdOfHost);
        assert get("/getSessionList").jsonPath().getList("genre").contains(testGenre);
        assert get("/getSessionList").jsonPath().getList("isOnline").contains(testIsOnline);
    }
}
