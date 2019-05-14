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

public class PostJoinRequest extends VolleyStringTemplate {
    private SessionManager session;
    private String email;
    private Activity activity;

    public PostJoinRequest(Resources r, SessionManager session, String email, Activity activity) {
        super();
        this.r = r;
        this.session = session;
        this.activity = activity;
    }

    @Override
    protected void handleString(String response, View view) {
        String result = "";
        Log.d("Response.Join", response);
        switch (Integer.parseInt(response)) {
            case -11:
                result = r.getString(R.string.join_error_joined);
                break;
            case -10:
                result = r.getString(R.string.join_error_full);
                break;
            case 0:
                result = r.getString(R.string.join_success);
                break;
            default:
                result = r.getString(R.string.new_error);
                break;
        }
            Snackbar.make(view, result, 5000).show();
        }
}
