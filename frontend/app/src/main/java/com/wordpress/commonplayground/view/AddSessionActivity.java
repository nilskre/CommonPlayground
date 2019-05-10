package com.wordpress.commonplayground.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.model.Validator;
import com.wordpress.commonplayground.network.PostSessionRequest;
import com.wordpress.commonplayground.viewmodel.SessionManager;

import java.util.Calendar;
import java.util.HashMap;

public class AddSessionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPublish;
    private ImageButton btnDatePicker, btnTimePicker;
    private TextInputLayout titleView, gameView, placeView, dateView, timeView, numberOfPlayersView, descriptionView;
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
        type_spinner = findViewById(R.id.type_spinner);
        genre_spinner = findViewById(R.id.genre_spinner);

        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateView(i);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        setOnclickListeners();
        accessUIInputFields();
        session = new SessionManager(getApplicationContext());
    }

    private void setOnclickListeners() {
        btnPublish = findViewById(R.id.ButtonPublish);
        btnPublish.setOnClickListener(this);

        btnDatePicker = findViewById(R.id.btn_date);
        btnDatePicker.setOnClickListener(this);

        btnTimePicker = findViewById(R.id.btn_time);
        btnTimePicker.setOnClickListener(this);
    }

    private void accessUIInputFields() {
        titleView = findViewById(R.id.TitleInput);
        gameView = findViewById(R.id.GameInput);
        placeView = findViewById(R.id.PlaceInput);
        dateView = findViewById(R.id.DateInput);
        timeView = findViewById(R.id.TimeInput);
        numberOfPlayersView = findViewById(R.id.PlayersInput);
        descriptionView = findViewById(R.id.DescriptionInput);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == btnPublish) {
            sendRequestToBackend(view);
            if (!cancel) {
                returnToMainActivity();
            }
        }

        if (view == btnDatePicker) {
            getCurrentDate();
        }

        if (view == btnTimePicker) {
            getCurrentTime();
        }

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

    public void getCurrentDate() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        launchDatePickerDialog();
    }

    private void launchDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateView.getEditText().setText(((dayOfMonth >= 10) ? "" : "0") + dayOfMonth + "-" + ((monthOfYear + 1 >= 10) ? "" : "0") + (monthOfYear + 1) + "-" + year);
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
                        timeView.getEditText().setText(((hourOfDay >= 10) ? "" : "0") + hourOfDay + ":" + ((minute >= 10) ? "" : "0") + minute);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    public void sendRequestToBackend(View view) {
        resetErrors();

        if (validInput()) {
            focusView.requestFocus();
        } else {
            description = descriptionView.getEditText().getText().toString();
            genre = genre_spinner.getSelectedItem().toString();
            type = type_spinner.getSelectedItem().toString();
            PostSessionRequest request = new PostSessionRequest(this.getResources());

            HashMap<String, String> parameters = new HashMap<String, String>();
            parameters.put("title", title);
            parameters.put("description", description);
            parameters.put("game", game);
                    if (placeView.getVisibility() != View.GONE) {
                        parameters.put("place", place);
                    }
            parameters.put("date", date);
            parameters.put("time", time);
            parameters.put("numberOfPlayers", numberOfPlayers);
            parameters.put("idOfHost", session.getUserDetails().get(SessionManager.KEY_ID));
            parameters.put("genre", genre);
            parameters.put("isOnline", type);

            request.stringRequest("postNewSession", "PostSession", this.getApplication(), parameters, view);
        }
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

    protected void updateView(int item){
        Log.d("Selection", Integer.toString(item));
        if (item==0){
           placeView.setVisibility(View.GONE);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.online_genres, android.R.layout.simple_spinner_item);
            genre_spinner.setAdapter(adapter);
        }else{
            placeView.setVisibility(View.VISIBLE);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.offline_genres, android.R.layout.simple_spinner_item);
            genre_spinner.setAdapter(adapter);
        }

    }

    private boolean validInput() {
        checkForValidNumberOFPlayers();
        checkForValidTime();
        checkForValidDate();
        if (placeView.getVisibility() != View.GONE) {
            checkForValidPlace();
        }
        checkForValidGame();
        checkForValidTitle();
        return cancel;
    }

    private void checkForValidTitle() {
        title = titleView.getEditText().getText().toString();
        if (checkForAnyInput(title)) {
            titleView.setError(getString(R.string.error_field_required));
            focusView = titleView;
            cancel = true;
        } else if (title.length() > 30) {
            titleView.setError(getString(R.string.error_too_long));
            focusView = titleView;
            cancel = true;
        }
    }

    private void checkForValidGame() {
        game = gameView.getEditText().getText().toString();
        if (checkForAnyInput(game)) {
            gameView.setError(getString(R.string.error_field_required));
            focusView = gameView;
            cancel = true;
        } else if (game.length() > 30) {
            gameView.setError(getString(R.string.error_too_long));
            focusView = gameView;
            cancel = true;
        }
    }

    private void checkForValidPlace() {
        place = placeView.getEditText().getText().toString();
        if (checkForAnyInput(place)) {
            placeView.setError(getString(R.string.error_field_required));
            focusView = placeView;
            cancel = true;
        } else if (!Validator.checkForValidPlace(place, this)) {
            placeView.setError(getString(R.string.error_wrong_place));
            focusView = placeView;
            cancel = true;
        }
    }

    private void checkForValidDate() {
        date = dateView.getEditText().getText().toString();
        if (checkForAnyInput(date)) {
            dateView.setError(getString(R.string.error_field_required));
            focusView = dateView;
            cancel = true;
        }
    }

    private void checkForValidTime() {
        time = timeView.getEditText().getText().toString();
        if (checkForAnyInput(time)) {
            timeView.setError(getString(R.string.error_field_required));
            focusView = timeView;
            cancel = true;
        }
    }

    private void checkForValidNumberOFPlayers() {
        numberOfPlayers = numberOfPlayersView.getEditText().getText().toString();
        if (TextUtils.isEmpty(numberOfPlayers)) {
            numberOfPlayersView.setError(getString(R.string.error_field_required));
            focusView = numberOfPlayersView;
            cancel = true;
        }
    }

    private boolean checkForAnyInput(String input) {
        return input.trim().length() <= 0;
    }
}