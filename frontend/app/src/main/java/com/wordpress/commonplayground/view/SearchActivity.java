package com.wordpress.commonplayground.view;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.model.Validator;
import com.wordpress.commonplayground.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSearch;
    private Spinner type_spinner, genre_spinner;
    private String type, place;
    private TextInputEditText placeView;
    private boolean cancel = false;
    private List<?> sessionList;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        type_spinner = findViewById(R.id.type_spinner);
        genre_spinner = findViewById(R.id.genre_spinner);
        placeView = findViewById(R.id.PlaceInputField);

        btnSearch = findViewById(R.id.ButtonPublish);
        btnSearch.setOnClickListener(this);
        mainActivityViewModel = new MainActivityViewModel(getApplication());

        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateView(i);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing
            }
        });
    }

    private void updateView(int item) {
        Log.d("Selection", Integer.toString(item));
        if (item==0){
            placeView.setVisibility(View.GONE);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.search_online_genres, android.R.layout.simple_spinner_dropdown_item);
            genre_spinner.setAdapter(adapter);
        }else{
            placeView.setVisibility(View.VISIBLE);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.search_offline_genres, android.R.layout.simple_spinner_dropdown_item);
            genre_spinner.setAdapter(adapter);
        }

    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnSearch)) {
            resetErrors();
            if (placeView.getVisibility() != View.GONE) {
                checkForValidPlace();
            }
            if(!cancel) {
                findSessions();
                if (sessionList == null) {
                    Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), "Couldn't find any sessions", 2000).show();
                } else {
                    Intent openSearchResultActivity = new Intent(getApplicationContext(), SearchResultActivity.class);
                    Bundle b = new Bundle();
                    b.putParcelableArrayList("Sessions", (ArrayList<? extends Parcelable>) sessionList);
                    openSearchResultActivity.putExtras(b);
                    startActivity(openSearchResultActivity);
                }
            }
        }
    }

    private void resetErrors() {
        placeView.setError(null);
        cancel = false;
    }

    private void checkForValidPlace() {
        place = placeView.getText().toString();
        if(!checkForAnyInput(place)) {
            placeView.setError(getString(R.string.error_field_required));
            cancel = true;
        } else if (!Validator.checkForValidPlace(place)) {
            placeView.setError(getString(R.string.error_wrong_place));
            cancel = true;
        }
    }

    private boolean checkForAnyInput(String input) {
        return input.trim().length() > 0;
    }

    private String getUrl() {
        type = type_spinner.getSelectedItem().toString();

        String api = "findSessions";
        String url = api + "?isOnline=" + type + "&genre=" + genre_spinner.getSelectedItemId();

        if ("Offline".equals(type)) {
            url += "&place=" + place;
        }

        return url;
    }

    private void findSessions() {
        sessionList = mainActivityViewModel.getSessions(getUrl()).getValue();
    }
}