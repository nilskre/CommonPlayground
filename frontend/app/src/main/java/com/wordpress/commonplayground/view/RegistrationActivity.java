package com.wordpress.commonplayground.view;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.model.Validator;
import com.wordpress.commonplayground.network.PostRegistrationRequest;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    private EditText mUsernameView, mEmailView, mPasswordView, mPasswordConfirmView;
    private String username, email, password, passwordConfirm;
    private boolean cancel = false;
    private View focusView = null;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupRegisteringForm();
    }

    @SuppressLint("NewApi")
    private void setupRegisteringForm() {
        mUsernameView = findViewById(R.id.username);
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mPasswordConfirmView = findViewById(R.id.password_confirm);
        Button mRegistrationButton = findViewById(R.id.registration_button);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptRegister(textView);
                    return true;
                }
                return false;
            }
        });

        mRegistrationButton.setOnClickListener(new OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                attemptRegister(view);
            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    @SuppressLint("NewApi")
    private void attemptRegister(View view) {
        resetErrors();
        storeRegisteringCredentials();

        checkIfPasswordsAreEqual();
        checkForValidPassword();
        checkForValidEMailAddress();
        checkEmpty(username, mUsernameView);

        if (cancel) {
            focusView.requestFocus();
        } else {
            requestToBackendRegister(view);
        }
    }

    private void resetErrors() {
        mUsernameView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mPasswordConfirmView.setError(null);
        cancel = false;
    }

    private void storeRegisteringCredentials() {
        username = mUsernameView.getText().toString();
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();
        passwordConfirm = mPasswordConfirmView.getText().toString();
    }

    @SuppressLint("NewApi")
    private void checkForValidPassword() {
        checkEmpty(password, mPasswordView);

        String validPassword = Validator.checkForValidPassword(password, this);
        if (!validPassword.isEmpty()) {
            mPasswordView.setError(validPassword);
            focusView = mPasswordView;
            cancel = true;
        }
    }

    @SuppressLint("NewApi")
    private void checkForValidEMailAddress() {
        String errorEmail = Validator.checkForValidEmail(email, this);
        if (!errorEmail.isEmpty()) {
            mEmailView.setError(errorEmail);
            focusView = mEmailView;
            cancel = true;
        }
    }

    private void checkIfPasswordsAreEqual() {
        boolean passwordsEqual = Validator.isPasswordConfirmed(password, passwordConfirm);
        if (!passwordsEqual) {
            mPasswordConfirmView.setError(getString(R.string.error_invalid_password_confirm));
            focusView = mPasswordConfirmView;
            cancel = true;
        }
    }

    private void checkEmpty(String input, EditText view){
        if (TextUtils.isEmpty(input)) {
            view.setError(getString(R.string.error_field_required));
            focusView = view;
            cancel = true;
        }
    }

    private void requestToBackendRegister(View view) {

        HashMap<String, String> parameters = new HashMap<String, String>(){
            {
                put("username", username);
                put("email", email);
                put("password", password);
            }};
        PostRegistrationRequest request = new PostRegistrationRequest(this);
        request.stringRequest("registerNewUser", "Registration", parameters, view);
    }
}