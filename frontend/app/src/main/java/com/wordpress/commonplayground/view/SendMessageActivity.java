package com.wordpress.commonplayground.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.network.PostMessageRequest;
import com.wordpress.commonplayground.viewmodel.SessionManager;

import java.util.HashMap;

public class SendMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private String username, title, message;
    private EditText usernameView, titleView, messageView;
    private boolean cancel = false;
    private View focusView = null;
    private SessionManager credentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        Button btnSend = findViewById(R.id.ButtonSend);
        btnSend.setOnClickListener(this);
        usernameView = findViewById(R.id.usernameInputField);
        titleView = findViewById(R.id.messageTitleInputField);
        messageView = findViewById(R.id.messageInputField);
        credentials = new SessionManager(this);
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
            //  returnToMainActivity();
        }
    }

    private void sendRequestToBackend() {

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("userID", credentials.getUserDetails().get(SessionManager.KEY_ID));
        parameters.put("receiverName", usernameView.getEditableText().toString());
        parameters.put("messageTitle", titleView.getEditableText().toString());
        parameters.put("messageContent", messageView.getEditableText().toString());

        PostMessageRequest request = new PostMessageRequest();
        request.stringRequest("sendComment", "Send Message", parameters, this.findViewById(android.R.id.content));
    }


    private void readFields() {
        username =  usernameView.getText().toString();
        title = titleView.getText().toString();
        message = messageView.getText().toString();
    }

    private void resetErrors() {
        usernameView.setError(null);
        messageView.setError(null);
        cancel = false;
    }

    private boolean validateInput() {
        checkForAnyInput(message, messageView);
        checkForAnyInput(title, titleView);
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
        }, 5000);
    }
}
