package commonplayground.controller.cucumber.api;

import commonplayground.Application;
import cucumber.api.java.en.And;
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

public class JoinStepDefinitions /*extends CucumberRuntime*/{
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private StringBuilder myJoinedSessions;

    @When("I send a join request for one session")
    public void iSendAJoinRequestForOneSession() {
        try {
            String body =
                    "userID=" + URLEncoder.encode(GlobalUserId.getNormalUserID(), "UTF-8") + "&" +
                            "sessionID=" + URLEncoder.encode(GlobalSessionId.getSessionID(), "UTF-8");

            URL url = new URL("http://localhost:8080/joinRequestForSession");
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

            String sessionID = "-42";
            for (String line; (line = reader.readLine()) != null; ) {
                sessionID = line;
            }
            GlobalSessionId.setSessionID(sessionID);

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }

    @And("The Session Host approves the request {string}")
    public void theSessionHostApprovesTheRequest(String joinAccepted) {
        System.out.println("EXEC");
        try {
            String body =
                    "userID=" + URLEncoder.encode(GlobalUserId.getSessionHostUserID(), "UTF-8") + "&" +
                            "messageID=" + URLEncoder.encode(GlobalMessageId.getMessageID(), "UTF-8") + "&" +
                            "joinAccepted=" + URLEncoder.encode(joinAccepted, "UTF-8");

            URL url = new URL("http://localhost:8080/joinResponse");
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

            String liner = "-42";
            for (String line; (line = reader.readLine()) != null; ) {
                liner += line;
            }
            System.out.println("LINER: " + liner);

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }

    @Then("I have joined the session")
    public void iHaveJoinedTheSession() {
        try {
            String body =
                    "userID=" + URLEncoder.encode(GlobalUserId.getNormalUserID(), "UTF-8");

            URL url = new URL("http://localhost:8080/getMyJoinedSessions");
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

            String liner = "-42";
            for (String line; (line = reader.readLine()) != null; ) {
                liner = line;
            }

            System.out.println("JOINED SESSIONS: " + liner);

            assert liner.contentEquals("[{\"id\":7,\"title\":\"Card Game\",\"description\":\"Card fun\",\"game\":\"Doppelkopf\",\"place\":\"Schlosspark\",\"date\":\"22-11-2024\",\"time\":\"12:00\",\"numberOfPlayers\":4,\"idOfHost\":6,\"genre\":\"genre\",\"isOnline\":\"false\",\"users\":[{\"id\":6,\"username\":\"User\",\"password\":\"$2a$10$sxlRSBsfop1JBhk2rwFRWuz4yKClO22wZzZM6Y.68dfVz6pbIq2gq\",\"email\":\"a@b.c\",\"messages\":[]},{\"id\":10,\"username\":\"Hello\",\"password\":\"$2a$10$9tIA6v.DqR8NYWLZ4tz16.l5McD5MOT62kbQqw5ja06Kag3IlcF2S\",\"email\":\"d@e.f\",\"messages\":[{\"id\":12,\"type\":\"Info\",\"authorName\":\"User\",\"title\":\"Join successful\",\"description\":\"Join to session Card Game was successful\",\"userIdWhoWantsToJoin\":null,\"sessionIdUserWantsToJoin\":null}]}],\"userWantToJoin\":[]}]");

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }

    @Then("I have left the session")
    public void iHaveLeftTheSession() {
        try {
            String body =
                    "userID=" + URLEncoder.encode(GlobalUserId.getNormalUserID(), "UTF-8");

            URL url = new URL("http://localhost:8080/getMyJoinedSessions");
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

            myJoinedSessions = new StringBuilder();
            for (String line; (line = reader.readLine()) != null; ) {
                myJoinedSessions.append(line);
            }
            log.info("Response of getMyJoinedSessions: " + myJoinedSessions);

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }
}
