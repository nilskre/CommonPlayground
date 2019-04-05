package com.wordpress.commonplayground.view;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.viewmodel.SessionManager;
import com.wordpress.commonplayground.model.Validator;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
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
        setupViews();
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

    private void setupViews() {
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
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

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/login";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String result = "";
                boolean success = false;
                Log.d("Response.Login", response);
                switch (Integer.parseInt(response)){
                    case -5: result = getString(R.string.login_error); break;
                    case -4: result = getString(R.string.username_error); break;
                    case -1: result = getString(R.string.new_error); break;
                    default: success = true; break;
                }
                if (success) {
                    session.createLoginSession(response.toString(), email);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Snackbar.make(view, result, 5000).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Login", String.valueOf(error));
                Snackbar.make(view, getString(R.string.new_error), 5000)
                        .setAction("Action", null).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<>();
                MyData.put("email", email);
                MyData.put("password", password);
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }

    @Override
    public void onBackPressed() {
        //Man könnte wohl auch einfach nichts tun... vllt?
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


}

