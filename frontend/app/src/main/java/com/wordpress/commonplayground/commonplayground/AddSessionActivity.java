package com.wordpress.commonplayground.commonplayground;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddSessionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button publish, btnDatePicker, btnTimePicker;
    private TextInputLayout title, game, place, date, time, numberOfPlayers, description;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);

        publish = (Button) findViewById(R.id.ButtonPublish);
        publish.setOnClickListener(this);

        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnDatePicker.setOnClickListener(this);

        btnTimePicker = (Button) findViewById(R.id.btn_time);
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
            post();
        }

        if (v == btnDatePicker) {
            getDate();
        }

        if (v == btnTimePicker) {
            getTime();
        }

    }

    private void getTime() {
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

                        time.getEditText().setText(((hourOfDay >= 10) ? "" : "0") + hourOfDay + ":" + ((minute >= 10) ? "" : "0") + minute);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    public void post() {
        /*get screen content*/
        final String sessionTitle = title.getEditText().getText().toString();
        final String sessionGame = game.getEditText().getText().toString();
        final String sessionPlace = place.getEditText().getText().toString();
        final String sessionDate = date.getEditText().getText().toString();
        final String sessionTime = time.getEditText().getText().toString();
        final String sessionPlayers = numberOfPlayers.getEditText().getText().toString();
        final String sessionDesc = description.getEditText().getText().toString();

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
                MyData.put("title", sessionTitle); //Add the data you'd like to send to the server.
                MyData.put("description", sessionDesc); //Add the data you'd like to send to the server.
                MyData.put("game", sessionGame); //Add the data you'd like to send to the server.
                MyData.put("place", sessionPlace); //Add the data you'd like to send to the server.
                MyData.put("date", sessionDate); //Add the data you'd like to send to the server.
                MyData.put("numberOfPlayers", sessionPlayers); //Add the data you'd like to send to the server.
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }

    public void getDate() {
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

                        date.getEditText().setText(((dayOfMonth >= 10) ? "" : "0") + dayOfMonth + "-" + ((monthOfYear + 1 >= 10) ? "" : "0") + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }
}
