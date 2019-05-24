package com.wordpress.commonplayground.network;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.view.MainActivity;
import com.wordpress.commonplayground.viewmodel.SessionManager;

import java.util.HashMap;

public class PostLoginRequest extends VolleyStringTemplate {
    private final SessionManager session;
    private final String email;
    private final Activity activity;

    public PostLoginRequest(SessionManager session, String email, Activity activity) {
        super();
        this.session = session;
        this.email = email;
        this.activity = activity;
    }

    @Override
    protected void handleString(String response, View view) {
        Log.d("Login", response);
        HashMap<Integer,String> snackText = new HashMap <Integer, String> (){{
            put (-5, view.getResources().getString(R.string.login_error));
            put (-4, view.getResources().getString(R.string.username_error));
            put (-1, view.getResources().getString(R.string.new_error));
        }};
        if (Integer.parseInt(response)>0) {
            session.createLoginSession(response, email);
            Intent i = new Intent(activity.getApplicationContext(), MainActivity.class);
            activity.startActivity(i);
            activity.finish();
        } else {
            Snackbar.make(view, snackText.get(Integer.parseInt(response)), 5000).show();
        }
    }
}
