import hello.Application;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PostNewSessionControllerTest {

    @BeforeClass
    public static void startSpringBoot(){
        SpringApplication.run(Application.class);
    }

    @Test
    public void testPostNewSessionController(){
        try {
            String body =
                    "name=" + URLEncoder.encode( "NAMETEST", "UTF-8" ) + "&" +
                    "description=" + URLEncoder.encode( "DESCIPTIONTEST", "UTF-8" ) + "&" +
                    "game=" + URLEncoder.encode( "GAMETEST", "UTF-8" ) + "&" +
                    "place=" + URLEncoder.encode( "PLACETEST", "UTF-8" ) + "&" +
                    "date=" + URLEncoder.encode( "DATETEST", "UTF-8" ) + "&" +
                    "numberOfPlayers=" + URLEncoder.encode( Integer.toString(2), "UTF-8" );

            URL url = new URL( "http://localhost:8080/postNewSession" );
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod( "POST" );
            connection.setDoInput( true );
            connection.setDoOutput( true );
            connection.setUseCaches( false );
            connection.setRequestProperty( "Content-Type",
                    "application/x-www-form-urlencoded" );
            connection.setRequestProperty( "Content-Length", String.valueOf(body.length()) );

            OutputStreamWriter writer = new OutputStreamWriter( connection.getOutputStream() );
            writer.write( body );
            writer.flush();


            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()) );

            for ( String line; (line = reader.readLine()) != null; )
            {
                System.out.println( line );
            }

            writer.close();
            reader.close();
        } catch (Exception e){
            assert false;
        }
    }
}