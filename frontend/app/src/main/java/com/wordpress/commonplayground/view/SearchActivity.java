package com.wordpress.commonplayground.view;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.wordpress.commonplayground.R;

public class SearchActivity extends AppCompatActivity {

    private Spinner type_spinner, genre_spinner;
    private TextInputLayout placeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        type_spinner = findViewById(R.id.type_spinner);
        genre_spinner = findViewById(R.id.genre_spinner);
        placeView = findViewById(R.id.PlaceInput);

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
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.online_genres, android.R.layout.simple_spinner_item);
            genre_spinner.setAdapter(adapter);
        }else{
            placeView.setVisibility(View.VISIBLE);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.offline_genres, android.R.layout.simple_spinner_item);
            genre_spinner.setAdapter(adapter);
        }

    }
}
