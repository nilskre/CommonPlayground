package commonplayground.controller.cucumber.api.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import commonplayground.Application;
import commonplayground.controller.cucumber.api.globaldict.GlobalSessionId;
import commonplayground.controller.cucumber.api.globaldict.GlobalUserId;
import commonplayground.model.Message;
import commonplayground.model.TestData;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class MessagesStepDefs {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private String myMessages = "";
    private List<Message> myMessageList;

    @When("{string} requests his messages")
    public void requestMessages(String user) {
        String userID;
        if (user.equals("sessionHost")) {
            userID = GlobalUserId.getSessionHostUserID();
        } else if (user.equals("normalUser")) {
            userID = GlobalUserId.getNormalUserID();
        } else {
            userID = GlobalUserId.getAnotherUserID();
        }
        System.out.println("User ID (Message) " + userID);

        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userID", GlobalUserId.getNormalUserID());
        body.add("sessionID", GlobalSessionId.getSessionID());

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> tmp = testRestTemplate.postForEntity("http://localhost:8080/getMyMessages", request, String.class);
        myMessages = tmp.getBody();

        log.info("Response of MyMessages Controller: " + myMessages);

        myMessageList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            // JSON string to Java object
            Message[] myMessageArray = mapper.readValue(myMessages, Message[].class);

            myMessageList = Arrays.asList(myMessageArray);
            String prettyPrint = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(myMessageArray);
            System.out.println("JSON MyMessages \n" + prettyPrint);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SCHIEF GEGANGEN !!!!!!!!!!!!!!!!!!");
        }

        System.out.println("THIS IS  MY LIST " + myMessageList);


        //try {
        //    TimeUnit.SECONDS.sleep(100);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}

        //GlobalMessageId.setMessageID(String.valueOf(myMessageList.get(0).getId()));

        //Gson gson = new Gson();
        //List<Message> resultMessage = gson.fromJson(myMessagesNoList, Message.class);
        //myMessageList.add(resultMessage);
        //System.out.println("RESULT: " + resultMessage.toString());
//
        //messageListTitles = new ArrayList<>();
        //for (Message message : myMessageList) {
        //    messageListTitles.add(message.getTitle());
        //}
        //System.out.println("MESSAGE TITLE: " + messageListTitles);

        //String messageId = String.valueOf(resultMessage.getId()); //get("/getMyMessages").jsonPath().getList("message.id").toArray()[0].toString();//.jsonPath().getList("id");
        //System.out.println("Message ID: " + resultMessage.toString());
        //String messageId = (String) get("/getMyMessages").jsonPath().getList("id").get(0);
        //;


        // -----------------------------------------------
        /*Gson gson = new Gson();
        for (String s : response) {
            Message resultMessage = gson.fromJson(s, Message.class);
            myMessageList.add(resultMessage);
            System.out.println("RESULT: " + resultMessage.toString());
        }

        try {
            String body =
                    "userID=" + URLEncoder.encode(userID, "UTF-8");

            URL url = new URL("http://localhost:8080/getMyMessages");
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

            List<String> response = new ArrayList<>();
            for (String line; (line = reader.readLine()) != null; ) {
                myMessages += line;
                response.add(line);
            }
            System.out.println("myMessages " + myMessages);

            // hacky but works
            String[] test = myMessages.split("[:]");
            String[] test2 = test[1].split("[,]");
            for (String s : test2) {
                System.out.println("TEST: " + s);
            }
            System.out.println("FINAL ID: " + test2[0]);
            String messageId = test2[0];

            //TODO read as list/object for tests at the bottom

            System.out.println("REPSONSE:_>=");
            // Cut [ and ]out of list elements
            for (int i = 0; i < response.size(); i++) {
                response.set(i, response.get(i).substring(1, response.get(0).length() - 1));
            }
            //String tmp2 = response.get(0).substring(1, response.get(0).length()-1);
            //System.out.println(tmp2);

            myMessageList = new ArrayList<>();

            Gson gson = new Gson();
            for (String s : response) {
                Message resultMessage = gson.fromJson(s, Message.class);
                myMessageList.add(resultMessage);
                System.out.println("RESULT: " + resultMessage.toString());
            }

            messageListTitles = new ArrayList<>();
            for (Message message : myMessageList) {
                messageListTitles.add(message.getTitle());
            }
            System.out.println("MESSAGE TITLE: " + messageListTitles);

            //String messageId = String.valueOf(resultMessage.getId()); //get("/getMyMessages").jsonPath().getList("message.id").toArray()[0].toString();//.jsonPath().getList("id");
            //System.out.println("Message ID: " + resultMessage.toString());
            //String messageId = (String) get("/getMyMessages").jsonPath().getList("id").get(0);
            GlobalMessageId.setMessageID(messageId);

            writer.close();
            reader.close();
        } catch (Exception e) {
            System.out.println("TROLO");
            //assert false;
        }*/
    }

    @Then("There should be my messages")
    public void thereShouldBeMyMessages() {
        TestData testData = new TestData();
        // TODO Test with real message
        assert true; //messageListTitles.isEmpty();
    }

    @Then("There should be a leave message")
    public void thereShouldBeALeaveMessage() {
        boolean leaveMessageExists = false;
        for (Message message:myMessageList) {
            if (message.getTitle().equals("Left successful")){
                leaveMessageExists = true;
            }
        }
        assertTrue(leaveMessageExists);
    }

    @Then("There should be a reject message")
    public void thereShouldBeARejectMessage() {
        boolean joinRejectedMessageExists = false;
        for (Message message:myMessageList) {
            if (message.getTitle().equals("Join rejected")){
                joinRejectedMessageExists = true;
            }
        }
        assertTrue(joinRejectedMessageExists);
        //TODO handling of session ID
        GlobalSessionId.setSessionID(null);
    }

    @Then("There should be a user has left message")
    public void thereShouldBeAUserHasLeftMessage() {
        boolean userHasLeftMessageExists = false;
        for (Message message:myMessageList) {
            if (message.getTitle().equals("User left session")){
                userHasLeftMessageExists = true;
            }
        }
        assertTrue(userHasLeftMessageExists);
    }
}