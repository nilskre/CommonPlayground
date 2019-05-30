package com.wordpress.commonplayground.view;

import com.wordpress.commonplayground.R;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSend;
    String username, message;
    private EditText usernameView, messageView;
    private boolean cancel = false;
    private View focusView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        btnSend = findViewById(R.id.ButtonSend);
        btnSend.setOnClickListener(this);
        usernameView = findViewById(R.id.usernameInputField);
        messageView = findViewById(R.id.messageInputField);
    }

    @Override
    public void onClick(View view) {
        resetErrors();
        readFields();
        if (validateInput()) {
            focusView.requestFocus();
        } else {
            sendRequestToBackend();
        }
        if (!cancel) {
            returnToMainActivity();
        }
    }

    private void sendRequestToBackend() {
        //TODO
    }

    private void readFields() {
        username =  usernameView.getText().toString();
        message = messageView.getText().toString();
    }

    private void resetErrors() {
        usernameView.setError(null);
        messageView.setError(null);
        cancel = false;
    }

    private boolean validateInput() {
        checkForAnyInput(message, messageView);
        checkForAnyInput(username, usernameView);
        return cancel;
    }

    private void checkForAnyInput(String input, View view) {
        if (input.trim().length() <= 0){
            EditText validate = (EditText) view;
            validate.setError(getString(R.string.error_field_required));
            focusView = view;
            cancel = true;
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
}
