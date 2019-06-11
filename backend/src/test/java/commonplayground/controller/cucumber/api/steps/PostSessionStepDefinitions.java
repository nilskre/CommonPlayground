package commonplayground.controller.cucumber.api.steps;

import commonplayground.Application;
import commonplayground.controller.cucumber.api.globaldict.GlobalSessionId;
import commonplayground.controller.cucumber.api.globaldict.GlobalUserId;
import commonplayground.model.Session;
import commonplayground.model.SessionRepository;
import commonplayground.model.TestData;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static io.restassured.RestAssured.get;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PostSessionStepDefinitions {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private TestData testData = new TestData();
    private Session testDataSession = testData.getTestSessions().get(0);

    private final SessionRepository sessionRepository;

    @Autowired
    public PostSessionStepDefinitions(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @When("I post a new Session with correct Data as test user type {string}")
    public void iPostANewSessionWithCorrectData(String testUserType) {
        String hostID;
        if (testUserType.equals("sessionHost") && GlobalUserId.getSessionHostUserID() != null) {
            hostID = GlobalUserId.getSessionHostUserID();
        } else if (testUserType.equals("normalUser") && GlobalUserId.getSessionHostUserID() != null) {
            hostID = GlobalUserId.getNormalUserID();
        } else {
            hostID = GlobalUserId.getAnotherUserID();
        }
        System.out.println("Posting HOSTID: " + hostID);

        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        String responsePostingApi = "Error";
        for (Session testSession : testData.getTestSessions()) {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

            body.add("title", testSession.getTitle());
            System.out.println("TITLE " + testSession.getTitle());
            body.add("description", testSession.getDescription());
            body.add("game", testSession.getGame());
            body.add("place", testSession.getPlace());
            body.add("date", testSession.getDate());
            body.add("time", testSession.getTime());
            body.add("numberOfPlayers", testSession.getNumberOfPlayers());
            body.add("idOfHost", hostID);
            body.add("genre", testSession.getGenre());
            body.add("isOnline", testSession.getIsOnline());

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

            responsePostingApi = testRestTemplate.postForObject("http://localhost:8080/postNewSession", request, String.class);
            log.info("Response of Posting Controller (SessionID): " + responsePostingApi);
        }

        try {
            Integer.parseInt(responsePostingApi);
            if (GlobalSessionId.getSessionID() == null) {
                GlobalSessionId.setSessionID(responsePostingApi);
                log.info("Posted sessions and set sessionID " + responsePostingApi);
            }
        } catch (Exception e) {
            assert false;
        }
    }

    @Then("^There should be my PostedSession with correct Data$")
    public void thereShouldBeMyPostedSessionWithCorrectData() {
        for (Session testSession : testData.getTestSessions()) {
            assertTrue(get("/getSessionList").jsonPath().getList("title").contains(testSession.getTitle()));
            assertTrue(get("/getSessionList").jsonPath().getList("description").contains(testSession.getDescription()));
            assertTrue(get("/getSessionList").jsonPath().getList("game").contains(testSession.getGame()));
            assertTrue(get("/getSessionList").jsonPath().getList("place").contains(testSession.getPlace()));
            assertTrue(get("/getSessionList").jsonPath().getList("date").contains(testSession.getDate()));
            assertTrue(get("/getSessionList").jsonPath().getList("time").contains(testSession.getTime()));
            assertTrue(get("/getSessionList").jsonPath().getList("numberOfPlayers").contains(testSession.getNumberOfPlayers()));
            assertTrue(get("/getSessionList").jsonPath().getList("idOfHost").contains(sessionRepository.findAllByTitle(testSession.getTitle()).get(0).getIdOfHost().intValue()));
            assertTrue(get("/getSessionList").jsonPath().getList("genre").contains(testSession.getGenre()));
            assertTrue(get("/getSessionList").jsonPath().getList("isOnline").contains(testSession.getIsOnline()));
        }
        GlobalSessionId.setSessionID(null);
    }

    @Then("Corrupt request sent and internal server error is returned")
    public void anInternalServerErrorIsReturned() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        body.add("title", testDataSession.getTitle());
        System.out.println("TITLE " + testDataSession.getTitle());
        body.add("description", testDataSession.getDescription());
        body.add("game", testDataSession.getGame());
        body.add("place", testDataSession.getPlace());
        body.add("date", testDataSession.getDate());
        body.add("time", testDataSession.getTime());
        body.add("numberOfPlayers", testDataSession.getNumberOfPlayers());
        body.add("idOfHost", -2);
        body.add("genre", testDataSession.getGenre());
        body.add("isOnline", testDataSession.getIsOnline());

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("http://localhost:8080/postNewSession", request, String.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
