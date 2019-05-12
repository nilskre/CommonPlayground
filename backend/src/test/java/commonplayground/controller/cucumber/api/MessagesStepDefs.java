package commonplayground.controller.cucumber.api;

import commonplayground.model.Message;
import commonplayground.model.TestData;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;

public class MessagesStepDefs /*extends CucumberRuntime*/{
    private String myMessages = "";
    private List<Message> messageList;
    private List<String> messageListTitles;

    @When("{string} requests his messages")
    public void requestMessages(String user) {
        String userID;
        if (user.equals("sessionHost")) {
            userID = GlobalUserId.getSessionHostUserID();
        } else {
            userID = GlobalUserId.getNormalUserID();
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
            for (String s:test2) {
                System.out.println("TEST: " + s);
            }
            System.out.println("FINAL ID: " + test2[0]);
            String messageId = test2[0];

            //TODO read as list/object for tests at the bottom

            System.out.println("REPSONSE:_>=");
            // Cut [ and ]out of list elements
            for (int i = 0; i < response.size(); i++) {
                response.set(i, response.get(i).substring(1, response.get(0).length()-1));
            }
            //String tmp2 = response.get(0).substring(1, response.get(0).length()-1);
            //System.out.println(tmp2);

            messageList = new ArrayList<>();

            Gson gson = new Gson();
            for (String s:response) {
                Message resultMessage = gson.fromJson(s, Message.class);
                messageList.add(resultMessage);
                System.out.println("RESULT: " + resultMessage.toString());
            }

            messageListTitles = new ArrayList<>();
            for (Message message:messageList) {
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
            assert false;
        }
    }

    @Then("There should be my messages")
    public void thereShouldBeMyMessages() {
        TestData testData = new TestData();
        //TODO Work with data from method above
        assert get("/getMyMessages").jsonPath().getList("title").contains(testData.getTestMessage().getTitle());
    }

    @Then("There should be a leave message")
    public void thereShouldBeALeaveMessage() {
        //assert messageList.get(0).getTitle().equals("Left successful");
        assert messageListTitles.contains("Left successful");

    }

    @Then("There should be a reject message")
    public void thereShouldBeARejectMessage() {
        //assert messageList.get(0).getTitle().equals("Join rejected");
        assert messageListTitles.contains("Join rejected");
    }

    @Then("There should be a user has left message")
    public void thereShouldBeAUserHasLeftMessage() {
        assert messageListTitles.contains("User left session");
    }


}
