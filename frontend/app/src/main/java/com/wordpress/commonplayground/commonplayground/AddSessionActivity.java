package com.wordpress.commonplayground.commonplayground;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                TextInputLayout time = (TextInputLayout) findViewById(R.id.TimeInput);
                TextInputLayout players = (TextInputLayout) findViewById(R.id.PlayersInput);
                TextInputLayout description = (TextInputLayout) findViewById(R.id.DescriptionInput);
                
            }
        });
    }
}
