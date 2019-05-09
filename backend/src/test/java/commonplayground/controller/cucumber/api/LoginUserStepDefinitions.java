package commonplayground.controller.cucumber.api;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginUserStepDefinitions {
    private String responseUserIdOrErrorCode = "";

    @When("I login with {string}{string}")
    public void iLoginWith(String email, String password) {
        try {
            String body =
                    "email=" + URLEncoder.encode(email, "UTF-8") + "&" +
                            "password=" + URLEncoder.encode(password, "UTF-8");

            URL url = new URL("http://localhost:8080/login");
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
                responseUserIdOrErrorCode = line;
            }
            System.out.println("responseUserIdOrErrorCode " + responseUserIdOrErrorCode);
            if (GlobalUserId.getHostUserID() == null) {
                GlobalUserId.setHostUserID(responseUserIdOrErrorCode);
            } else {
                GlobalUserId.setNormalUserID(responseUserIdOrErrorCode);
            }

            writer.close();
            reader.close();
        } catch (Exception e) {
            assert false;
        }
    }

    @Then("The response is my user ID")
    public void theResponseIsMyUserID() {
        try{
            Integer.parseInt(responseUserIdOrErrorCode);
        } catch (NumberFormatException e) {
            assert false;
        }
    }
}
