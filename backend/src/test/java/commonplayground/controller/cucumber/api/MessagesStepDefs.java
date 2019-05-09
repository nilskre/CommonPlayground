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

import static io.restassured.RestAssured.get;

public class MessagesStepDefs {
    private String myMessages = "";

    @When("I request my messages")
    public void iRequestMyMessages() {
        try {
            String body =
                    "userID=" + URLEncoder.encode(GlobalUserId.getHostUserID(), "UTF-8");

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
                myMessages = line;
            }
            System.out.println("myMessages " + myMessages);

            Gson gson = new Gson();
            Message resultMessage = gson.fromJson(myMessages, Message.class);
            System.out.println("RESULT: " + resultMessage);
            String messageId = String.valueOf(resultMessage.getId()); //get("/getMyMessages").jsonPath().getList("message.id").toArray()[0].toString();//.jsonPath().getList("id");
            System.out.println("Message ID: " + resultMessage.toString());
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

        assert get("/getMyMessages").jsonPath().getList("title").contains(testData.getTestMessage().getTitle());
    }
}
