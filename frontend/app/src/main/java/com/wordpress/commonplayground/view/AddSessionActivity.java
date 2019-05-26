package com.wordpress.commonplayground.view;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.model.Validator;
import com.wordpress.commonplayground.network.PostSessionRequest;
import com.wordpress.commonplayground.viewmodel.SessionManager;

import java.util.Calendar;
import java.util.HashMap;

public class AddSessionActivity extends AppCompatActivity {

    private  EditText titleView, gameView, placeView, dateView, timeView, numberOfPlayersView, descriptionView;
    private String title, game, place, date, time, numberOfPlayers, description, genre, type;
    private Spinner type_spinner, genre_spinner;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private SessionManager session;
    private boolean cancel = false;
    private View focusView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);
        setUpSpinners();
        setOnclickListeners();
        accessUIInputFields();
        session = new SessionManager(this);
    }

    private void setUpSpinners() {
        type_spinner = findViewById(R.id.type_spinner);
        genre_spinner = findViewById(R.id.genre_spinner);

        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateView(i);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing
            }
        });
    }

    private void setOnclickListeners() {
        Button btnPublish = findViewById(R.id.ButtonPublish);
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onPublish(v);
            }
         });

        ImageButton btnDatePicker = findViewById(R.id.btn_date);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentDate();
            }
        });

        ImageButton btnTimePicker = findViewById(R.id.btn_time);
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentTime();
            }
        });
    }

    private void onPublish(View v) {
        resetErrors();
        readFields();
        if (validInput()) {
            focusView.requestFocus();
        } else {
            sendRequestToBackend(v);
        }
        if (!cancel) {
            returnToMainActivity();
        }
    }

    private void readFields() {
       title =  titleView.getText().toString();
        place = placeView.getText().toString();
        game = gameView.getText().toString();
        date = dateView.getText().toString();
        numberOfPlayers = numberOfPlayersView.getText().toString();
        time = timeView.getText().toString();
    }

    private void accessUIInputFields() {
        titleView = findViewById(R.id.TitleInputField);
        gameView = findViewById(R.id.GameInputField);
        placeView = findViewById(R.id.PlaceInputField);
        dateView = findViewById(R.id.DateInputField);
        timeView = findViewById(R.id.TimeInputField);
        numberOfPlayersView = findViewById(R.id.PlayersInputField);
        descriptionView = findViewById(R.id.DescriptionInputField);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void returnToMainActivity() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        }, 100);
    }

    private void getCurrentDate() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        launchDatePickerDialog();
    }

    @SuppressLint("NewApi")
    private void launchDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String setDateTo = ((dayOfMonth >= 10) ? "" : "0") + dayOfMonth + "-" + ((monthOfYear + 1 >= 10) ? "" : "0") + (monthOfYear + 1) + "-" + year;
                        dateView.setText(setDateTo);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }

    private void getCurrentTime() {
        final Calendar calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);
        launchTimePickerDialog();
    }

    private void launchTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String setTimeTo = ((hourOfDay >= 10) ? "" : "0") + hourOfDay + ":" + ((minute >= 10) ? "" : "0") + minute;
                        timeView.setText(setTimeTo);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    private void sendRequestToBackend(View view) {
        description = descriptionView.getText().toString();
        genre = genre_spinner.getSelectedItem().toString();
        type = type_spinner.getSelectedItem().toString();

            HashMap<String, String> parameters = new HashMap<String, String>()
            {{
             put("title", title);
             put("description", description);
             put("game", game);
             put("date", date);
             put("time", time);
             put("numberOfPlayers", numberOfPlayers);
             put("idOfHost", session.getUserDetails().get(SessionManager.KEY_ID));
             put("genre", genre);
             put("isOnline", type);
             if ("offline".equals(type)) {
                put("place", place);
             }
            }};

        PostSessionRequest request = new PostSessionRequest();
        request.stringRequest("postNewSession", "PostSession", parameters, view);
    }


    private void resetErrors() {
        titleView.setError(null);
        gameView.setError(null);
        placeView.setError(null);
        dateView.setError(null);
        timeView.setError(null);
        numberOfPlayersView.setError(null);
        cancel = false;
    }

    private void updateView(int item) {
        ArrayAdapter<CharSequence> adapter;
        int array;
        if (item==0){
           placeView.setVisibility(View.GONE);
           array = (R.array.online_genres);
        }else{
            placeView.setVisibility(View.VISIBLE);
            array = (R.array.offline_genres);
        }
        adapter = ArrayAdapter.createFromResource(this, array, android.R.layout.simple_spinner_dropdown_item);
        genre_spinner.setAdapter(adapter);
    }


    private boolean validInput() {
        checkForAnyInput(numberOfPlayers, numberOfPlayersView);
        checkForAnyInput(time, timeView);
        checkForAnyInput(date, dateView);
        if ("offline".equals(genre)) {
            checkForValidPlace();
        }
        checkForValidGame();
        checkForValidTitle();
        return cancel;
    }

    private void checkForValidTitle() {
        checkForAnyInput(title,titleView);
        if (title.length() > 30) {
            titleView.setError(getString(R.string.error_too_long));
            focusView = titleView;
            cancel = true;
        }
    }

    private void checkForValidGame() {
        checkForAnyInput(game, gameView);
        if (game.length() > 30) {
            gameView.setError(getString(R.string.error_too_long));
            focusView = gameView;
            cancel = true;
        }
    }

    private void checkForValidPlace() {
        checkForAnyInput(place, placeView);
        if (!Validator.checkForValidPlace(place)) {
            placeView.setError(getString(R.string.error_wrong_place));
            focusView = placeView;
            cancel = true;
        }
    }

    private void checkForAnyInput(String input, View view) {
        if (input.trim().length() <= 0){
            EditText validate= (EditText ) view;
            validate.setError(getString(R.string.error_field_required));
            focusView = view;
            cancel = true;
        }
    }
}