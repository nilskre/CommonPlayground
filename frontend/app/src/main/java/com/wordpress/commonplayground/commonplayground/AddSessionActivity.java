package com.wordpress.commonplayground.commonplayground;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class AddSessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);

        Button publish = (Button) findViewById(R.id.ButtonPublish);
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputLayout title = (TextInputLayout) findViewById(R.id.TitleInput);
                TextInputLayout game = (TextInputLayout) findViewById(R.id.GameInput);
                TextInputLayout place = (TextInputLayout) findViewById(R.id.PlaceInput);
                TextInputLayout date = (TextInputLayout) findViewById(R.id.DateInput);
                TextInputLayout numberOfPlayers = (TextInputLayout) findViewById(R.id.PlayersInput);
                TextInputLayout description = (TextInputLayout) findViewById(R.id.DescriptionInput);

                try {
                    String body =
                            "name=" + URLEncoder.encode( title.getEditText().getText().toString(), "UTF-8" ) + "&" +
                                    "description=" + URLEncoder.encode( description.getEditText().getText().toString(), "UTF-8" ) + "&" +
                                    "game=" + URLEncoder.encode( game.getEditText().getText().toString(), "UTF-8" ) + "&" +
                                    "place=" + URLEncoder.encode( place.getEditText().getText().toString(), "UTF-8" ) + "&" +
                                    "date=" + URLEncoder.encode( date.getEditText().getText().toString(), "UTF-8" ) + "&" +
                                    "numberOfPlayers=" + URLEncoder.encode( numberOfPlayers.getEditText().getText().toString(), "UTF-8" );

                    //System.out.println(body);

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
        });
    }
}
