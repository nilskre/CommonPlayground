package com.wordpress.commonplayground.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wordpress.commonplayground.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

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
    }

    private void displayLogo() {
        ImageView logo = (ImageView) findViewById(R.id.logoView);
        logo.setImageDrawable(getResources().getDrawable(R.drawable.logo));
    }

    private void setupLoginForm() {
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
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
        Button mEmailLoginButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin(view);
            }
        });
    }

    private void setupRegisteringButton() {
        Button mRegisterButton = (Button) findViewById(R.id.register_button);
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
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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

        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (password.length() < 8) {
            mPasswordView.setError(getString(R.string.error_short_password));
            focusView = mPasswordView;
            cancel = true;
        } else if (password.length() > 30) {
            mPasswordView.setError(getString(R.string.error_long_password));
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
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
                String result = new String();
                boolean success = false;
                Log.d("Response.Login", response.toString());
                switch (Integer.parseInt(response.toString())){
                    case -5: result = getString(R.string.login_error); break;
                    case -4: result = getString(R.string.username_error); break;
                    case -1: result = getString(R.string.new_error); break;
                    default: success = true; break;
                }
                if (success) {
                    Intent openMain = new Intent(LoginActivity.this, MainActivity.class);
                    openMain.putExtra("userID", response.toString());
                    startActivity(openMain);
                }else{
                    Snackbar.make(view, result, 5000)
                        .setAction("Action", null).show();
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
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("email", email);
                MyData.put("password", password);
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }

    private boolean isEmailValid(String email) {
        String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+";
        Matcher matcher = Pattern.compile(validemail).matcher(email);
        return matcher.matches();
    }

    private boolean isPasswordValid(String password) {
        String validpassword = "^([a-zA-Z0-9@*#!?$&.-_]{8,30})$";
        Matcher matcher = Pattern.compile(validpassword).matcher(password);
        return matcher.matches();
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        //Man könnte wohl auch einfach nichts tun... vllt?
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}

