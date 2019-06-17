package commonplayground.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlaceAPI {

    public String sendRequestToPlaceAPI(String place){
        String city = "falsche PLZ";
        try {
            URL url = new URL("http://api.zippopotam.us/de/" + place);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
            { content.append(inputLine);
            }
            in.close();
            city= getCityOutOfResponse(content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    private String getCityOutOfResponse(String response){
        String city = new String();
        int iterationValue= 102;
        while (response.charAt(iterationValue) != '"') {
            city+= response.charAt(iterationValue);
            iterationValue++;
        }
        return city;
    }
}
