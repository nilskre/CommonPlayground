package com.wordpress.commonplayground.network;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.view.LoginActivity;

public class PostRegistrationRequest extends VolleyStringTemplate {
    Activity activity;

    public PostRegistrationRequest(Resources r, Activity activity) {
        super();
        this.r = r;
        this.activity = activity;
    }

    @Override
    protected void handleString(String response, View view) {
        String result = "";
        Log.d("Response.Register", response);
        switch (Integer.parseInt(response)) {
            case -3:
                result = r.getString(R.string.email_double_error);
                break;
            case -2:
                result = r.getString(R.string.username_double_error);
                break;
            case 0:
                result = r.getString(R.string.registration_succsess);
        }
        Snackbar.make(view, result, 5000).show();

        if (Integer.parseInt(response) == 0) {
            Intent openLoginActivity = new Intent(activity.getApplicationContext(), LoginActivity.class);
            activity.startActivity(openLoginActivity);
        }
    }
}
