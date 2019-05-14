package com.wordpress.commonplayground.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.model.Validator;
import com.wordpress.commonplayground.network.PostLoginRequest;
import com.wordpress.commonplayground.viewmodel.SessionManager;

import java.util.HashMap;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private EditText mEmailView;
    private EditText mPasswordView;
    private SessionManager session;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: started.");

        displayLogo();
        setupLoginForm();
        setupLoginButton();
        setupRegisteringButton();
        session = new SessionManager(getApplicationContext());
    }

    private void displayLogo() {
        ImageView logo = findViewById(R.id.logoView);
        logo.setImageDrawable(getResources().getDrawable(R.drawable.logo));
    }

    private void setupLoginForm() {
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin(textView);
                    return true;
                }
                return false;
            }
        });
    }

    private void setupLoginButton() {
        Button mEmailLoginButton = findViewById(R.id.email_sign_in_button);
        mEmailLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin(view);
            }
        });
    }

    private void setupRegisteringButton() {
        Button mRegisterButton = findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent openRegistrationActivity = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(openRegistrationActivity);
            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.*/
    private void attemptLogin(View view) {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        // Check for valid input from the bottom to the top that the focus is at the top if there are several mistakes
        // Check for a valid password.
        checkIfValidInput(email, password, view);
    }

    private void checkIfValidInput(String email, String password, View view) {
        boolean cancel = false;
        View focusView = null;

        String errorPassword = Validator.checkForValidPassword(password, this);
        if (!errorPassword.isEmpty()) {
            mPasswordView.setError(errorPassword);
            focusView = mPasswordView;
            cancel = true;
        }

        String errorEmail = Validator.checkForValidEmail(email, this);
        if (!errorEmail.isEmpty()) {
            mEmailView.setError(errorEmail);
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            requestLogin(view);
        }
    }

    private void requestLogin(View view) {
        /*get screen content*/
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("email", email);
        parameters.put("password", password);

        PostLoginRequest request = new PostLoginRequest(this.getResources(), session, email, this);
        request.stringRequest("login", "Login", getApplicationContext(), parameters, view);
    }

    @Override
    public void onBackPressed() {
        //Man könnte wohl auch einfach nichts tun... vllt?
        //android.os.Process.killProcess(android.os.Process.myPid());
        //System.exit(1);

        //Ich würde einfach nichts tun. Dann wird die App zwar nur über Home geschlossen,
        //was bei der anderen Variante auch nur geht und man hat durch das Überschreiben und nichts tun
        //keine hässliches Neustarten der App. Sie passiert einfach nichts.
    }
}