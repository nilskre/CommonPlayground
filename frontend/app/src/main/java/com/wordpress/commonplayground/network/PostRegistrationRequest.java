package com.wordpress.commonplayground.network;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.view.LoginActivity;

import java.util.HashMap;

public class PostRegistrationRequest extends VolleyStringTemplate {
    private final Activity activity;

    public PostRegistrationRequest(Activity activity) {
        super();
        this.activity = activity;
    }

    @Override
    protected void handleString(String response, View view) {
        Log.d("Response.Register", response);
        HashMap<Integer,String> snackText = new HashMap <Integer, String> (){{
            put (-3, view.getResources().getString(R.string.email_double_error));
            put (-2, view.getResources().getString(R.string.username_double_error));
            put (0, view.getResources().getString(R.string.registration_succsess));
        }};
        Snackbar.make(view, snackText.get(Integer.parseInt(response)), 5000).show();

        if (Integer.parseInt(response) == 0) {
            Intent openLoginActivity = new Intent(activity.getApplicationContext(), LoginActivity.class);
            activity.startActivity(openLoginActivity);
        }
    }
}
