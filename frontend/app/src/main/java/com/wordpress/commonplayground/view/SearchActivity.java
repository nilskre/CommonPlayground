package com.wordpress.commonplayground.view;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
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

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSearch;
    private Spinner type_spinner, genre_spinner;
    private TextInputLayout placeView;
    private String place;
    private boolean cancel = false;
    private View focusView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        type_spinner = findViewById(R.id.type_spinner);
        genre_spinner = findViewById(R.id.genre_spinner);
        placeView = findViewById(R.id.PlaceInput);

        btnSearch = findViewById(R.id.ButtonPublish);
        btnSearch.setOnClickListener(this);

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
                Intent openSearchResultActivity = new Intent(getApplicationContext(), SearchResultActivity.class);
                startActivity(openSearchResultActivity);
            }
        }
    }

    private void resetErrors() {
        placeView.setError(null);
        cancel = false;
    }

    private void checkForValidPlace() {
        place = placeView.getEditText().getText().toString();
        if (Validator.checkForAnyInput(place)) {
            placeView.setError(getString(R.string.error_field_required));
            focusView = placeView;
            cancel = true;
        } else if (!Validator.checkForValidPlace(place)) {
            placeView.setError(getString(R.string.error_wrong_place));
            focusView = placeView;
            cancel = true;
        }
    }
}
