package commonplayground.controller.cucumber.api.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import commonplayground.Application;
import commonplayground.controller.cucumber.api.globaldict.GlobalMessageId;
import commonplayground.controller.cucumber.api.globaldict.GlobalSessionId;
import commonplayground.controller.cucumber.api.globaldict.GlobalUserId;
import commonplayground.model.Message;
import commonplayground.model.UserRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class MessagesStepDefs {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private List<Message> myMessageList;

    private UserRepository userRepository;

    @Autowired
    public MessagesStepDefs(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @When("{string} requests his messages")
    public void requestMessages(String user) {
        String userID;
        if ("sessionHost".equals(user)) {
            userID = GlobalUserId.getSessionHostUserID();
        } else if ("normalUser".equals(user)) {
            userID = GlobalUserId.getNormalUserID();
        } else {
            userID = GlobalUserId.getAnotherUserID();
        }
        System.out.println("User ID (Message) " + userID);

        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userID", userID);
        body.add("sessionID", GlobalSessionId.getSessionID());

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> tmp = testRestTemplate.postForEntity("http://localhost:8080/getMyMessages", request, String.class);
        String myMessages = tmp.getBody();

        log.info("Response of MyMessages Controller: " + myMessages);

        myMessageList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            // JSON string to Java object
            Message[] myMessageArray = mapper.readValue(myMessages, Message[].class);

            myMessageList = Arrays.asList(myMessageArray);
            String prettyPrint = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(myMessageArray);
            System.out.println("\n JSON MyMessages \n" + prettyPrint);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!myMessageList.isEmpty() && myMessageList.get(0) != null) {
            GlobalMessageId.setMessageID(String.valueOf(myMessageList.get(0).getId()));
        }
    }

    @Then("There should be my messages")
    public void thereShouldBeMyMessages() {
        // tbd test with real message
    }

    @Then("There should be a leave message")
    public void thereShouldBeALeaveMessage() {
        boolean leaveMessageExists = false;
        for (Message message : myMessageList) {
            if (message.getTitle().equals("Left successful")) {
                leaveMessageExists = true;
            }
        }
        assertTrue(leaveMessageExists);
    }

    @Then("There should be a reject message")
    public void thereShouldBeARejectMessage() {
        boolean joinRejectedMessageExists = false;
        for (Message message : myMessageList) {
            if (message.getTitle().equals("Join rejected")) {
                joinRejectedMessageExists = true;
            }
        }
        assertTrue(joinRejectedMessageExists);
        GlobalSessionId.setSessionID(null);
    }

    @Then("There should be a user has left message")
    public void thereShouldBeAUserHasLeftMessage() {
        boolean userHasLeftMessageExists = false;
        for (Message message : myMessageList) {
            if (message.getTitle().equals("User left session")) {
                userHasLeftMessageExists = true;
            }
        }
        assertTrue(userHasLeftMessageExists);
    }

    @And("Delete leave message")
    public void deleteLeaveMessage() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        System.out.println(GlobalUserId.getNormalUserID() + " " + myMessageList.get(0).getId());

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userID", GlobalUserId.getNormalUserID());
        body.add("messageID", myMessageList.get(0).getId());

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> tmp = testRestTemplate.postForEntity("http://localhost:8080/removeMessage", request, String.class);
        String response = tmp.getBody();

        log.info("Response of Remove Messages Controller: " + response);
    }

    @When("The session host deletes his messages")
    public void theSessionHostDeletesHisMessages() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        for (Message message : myMessageList) {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            System.out.println(GlobalUserId.getNormalUserID() + " " + myMessageList.get(0).getId());

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("userID", GlobalUserId.getSessionHostUserID());
            System.out.println("IDIDIDIDIID " + message.getId());
            body.add("messageID", message.getId());

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

            ResponseEntity<String> tmp = testRestTemplate.postForEntity("http://localhost:8080/removeMessage", request, String.class);
            String response = tmp.getBody();

            log.info("Response of Remove Messages Controller: " + response);
        }
    }

    @Then("Corrupt request sent and internal server error is returned message")
    public void corruptRequestSentAndInternalServerErrorIsReturnedMessage() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userID", -2);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("http://localhost:8080/getMyMessages", request, String.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @And("When {string} send a message to {string}")
    public void whenISendAMessageTo(String userOne, String userTwo) {
        String userID;
        if ("sessionHost".equals(userOne)) {
            userID = GlobalUserId.getSessionHostUserID();
        } else if ("normalUser".equals(userOne)) {
            userID = GlobalUserId.getNormalUserID();
        } else {
            userID = GlobalUserId.getAnotherUserID();
        }

        String userIDTwo;
        if ("sessionHost".equals(userTwo)) {
            userIDTwo = GlobalUserId.getSessionHostUserID();
        } else if ("normalUser".equals(userTwo)) {
            userIDTwo = GlobalUserId.getNormalUserID();
        } else {
            userIDTwo = GlobalUserId.getAnotherUserID();
        }

        System.out.println("User ID (Message) " + userID);

        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userID", userID);
        body.add("receiverName", userRepository.findAllById(Long.parseLong(userIDTwo)).getUsername());
        body.add("messageTitle", "Testtitle");
        body.add("messageContent", "messageContent");

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> tmp = testRestTemplate.postForEntity("http://localhost:8080/sendComment", request, String.class);
        String myMessages = tmp.getBody();

        log.info("Response of Send Messages Controller: " + myMessages);
    }

    @Then("There is a message")
    public void thereIsAMessage() {
        //tbd
    }

    @Then("Corrupt request sent and internal server error is returned sendmessage")
    public void corruptRequestSentAndInternalServerErrorIsReturnedSendmessage() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userID", -2);
        body.add("receiverName", "Test");
        body.add("messageTitle", "Testtitle");
        body.add("messageContent", "messageContent");

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> tmp = testRestTemplate.postForEntity("http://localhost:8080/sendComment", request, String.class);
        String myMessages = tmp.getBody();

        log.info("Response of Send Messages Controller: " + myMessages);
    }

    @Then("Reciever does not exist sendmessage")
    public void recieverDoesNotExist() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userID", -2);
        body.add("receiverName", "DoesNotExist");
        body.add("messageTitle", "Testtitle");
        body.add("messageContent", "messageContent");

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> tmp = testRestTemplate.postForEntity("http://localhost:8080/sendComment", request, String.class);
        String myMessages = tmp.getBody();

        log.info("Response of Send Messages Controller: " + myMessages);
    }

    @And("{string} has new messages")
    public void hasNewMessages(String user) {
        String userID;
        if ("sessionHost".equals(user)) {
            userID = GlobalUserId.getSessionHostUserID();
        } else if ("normalUser".equals(user)) {
            userID = GlobalUserId.getNormalUserID();
        } else {
            userID = GlobalUserId.getAnotherUserID();
        }
        System.out.println("User ID (Message) " + userID);

        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userID", userID);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> tmp = testRestTemplate.postForEntity("http://localhost:8080/hasNewMessages", request, String.class);
        String hasNewMessagesAnswer = tmp.getBody();

        log.info("Response of HasNewMessages Controller: " + hasNewMessagesAnswer);

        assertTrue(Boolean.parseBoolean(hasNewMessagesAnswer));
    }

    @Then("Corrupt request sent and internal server error is returned hasNewMessage")
    public void corruptRequestSentAndInternalServerErrorIsReturnedHasNewMessage() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userID", -2);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("http://localhost:8080/hasNewMessages", request, String.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}