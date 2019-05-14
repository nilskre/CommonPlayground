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

public class PostLoginRequest extends VolleyStringTemplate {
    private SessionManager session;
    private String email;
    private Activity activity;

    public PostLoginRequest(Resources r, SessionManager session, String email, Activity activity) {
        super();
        this.r = r;
        this.session = session;
        this.email = email;
        this.activity = activity;
    }

    @Override
    protected void handleString(String response, View view) {
        String result = "";
        boolean success = false;
        Log.d("Response.Login", response);
        switch (Integer.parseInt(response)) {
            case -5:
                result = r.getString(R.string.login_error);
                break;
            case -4:
                result = r.getString(R.string.username_error);
                break;
            case -1:
                result = r.getString(R.string.new_error);
                break;
            default:
                success = true;
                break;
        }
        if (success) {
            session.createLoginSession(response, email);
            Intent i = new Intent(activity.getApplicationContext(), MainActivity.class);
            activity.startActivity(i);
            activity.finish();
        } else {
            Snackbar.make(view, result, 5000).show();
        }
    }
}
