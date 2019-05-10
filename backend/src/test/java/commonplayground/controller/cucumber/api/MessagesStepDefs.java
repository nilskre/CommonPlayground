package commonplayground.controller.cucumber.api;

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

public class MessagesStepDefs {
    private String myMessages = "";

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

            for (String line; (line = reader.readLine()) != null; ) {
                myMessages += line;
            }
            System.out.println("myMessages " + myMessages);

            String[] test = myMessages.split("[:]");
            String[] test2 = test[1].split("[,]");
            for (String s:test2) {
                System.out.println("TEST: " + s);
            }
            System.out.println("FINAL ID: " + test2[0]);
            String messageId = test2[0];

            //TODO read as list/object for tests at the bottom


            //Gson gson = new Gson();
            //Message resultMessage = gson.fromJson(myMessages, Message.class);
            //System.out.println("RESULT: " + resultMessage);
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
        //TODO Work with data from method above
        assert get("/getMyMessages").jsonPath().getList("title").contains("Left successful");
    }

    @Then("There should be a reject message")
    public void thereShouldBeARejectMessage() {
        //TODO Work with data from method above
        assert get("/getMyMessages").jsonPath().getList("title").contains("Join rejected");
    }
}
