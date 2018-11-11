package com.wordpress.commonplayground.commonplayground;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import static java.security.AccessController.getContext;

public class AddSessionActivity extends AppCompatActivity implements View.OnClickListener {

    Button publish, btnDatePicker, btnTimePicker;
    TextInputLayout title, game, place, date, time, numberOfPlayers, description;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);

        publish = (Button) findViewById(R.id.ButtonPublish);
        publish.setOnClickListener(this);

        btnDatePicker = (Button)findViewById(R.id.btn_date);
        btnDatePicker.setOnClickListener(this);

        btnTimePicker=(Button)findViewById(R.id.btn_time);
        btnTimePicker.setOnClickListener(this);

        title = (TextInputLayout) findViewById(R.id.TitleInput);
        game = (TextInputLayout) findViewById(R.id.GameInput);
        place = (TextInputLayout) findViewById(R.id.PlaceInput);
        date = (TextInputLayout) findViewById(R.id.DateInput);
        time = (TextInputLayout) findViewById(R.id.TimeInput);
        numberOfPlayers = (TextInputLayout) findViewById(R.id.PlayersInput);
        description = (TextInputLayout) findViewById(R.id.DescriptionInput);

    }

    @Override
    public void onClick(View v) {
        if (v == publish) {

           /* try {
               /*String body =
                        "name=" + URLEncoder.encode( title.getEditText().getText().toString(), "UTF-8" ) + "&" +
                                "description=" + URLEncoder.encode( description.getEditText().getText().toString(), "UTF-8" ) + "&" +
                                "game=" + URLEncoder.encode( game.getEditText().getText().toString(), "UTF-8" ) + "&" +
                                "place=" + URLEncoder.encode( place.getEditText().getText().toString(), "UTF-8" ) + "&" +
                                "date=" + URLEncoder.encode( date.getEditText().getText().toString(), "UTF-8" ) + "&" +
                                "numberOfPlayers=" + URLEncoder.encode( numberOfPlayers.getEditText().getText().toString(), "UTF-8" );

                Log.v("AddSessionActivity", body);

                URL url = new URL( "https://936a3ec9-22c9-44ac-8c08-0d942a1b7569.mock.pstmn.io" );
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
                reader.close();*/

                RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
                String url = "http://10.0.2.2:8080/postNewSession";
                StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //This code is executed if the server responds, whether or not the response contains data.
                        //The String 'response' contains the server's response.
                    }
                }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //This code is executed if there is an error.
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> MyData = new HashMap<String, String>();
                        MyData.put("Name", "This"); //Add the data you'd like to send to the server.
                        MyData.put("Description", "is a"); //Add the data you'd like to send to the server.
                        MyData.put("Game", "test"); //Add the data you'd like to send to the server.
                        return MyData;
                    }
                };

            try {
                Log.d("myTag", String.valueOf(MyStringRequest.getBody()));
            } catch (AuthFailureError authFailureError) {
                authFailureError.printStackTrace();
            }

            MyRequestQueue.add(MyStringRequest);


        /*    } catch (Exception e){
                assert false;
            }*/
        }

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            date.getEditText().setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            time.getEditText().setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}
