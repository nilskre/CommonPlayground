package com.wordpress.commonplayground.network;

import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.wordpress.commonplayground.R;

public class PostJoinRequest extends VolleyStringTemplate {
    private final Resources r;

    public PostJoinRequest(Resources r) {
        super();
        this.r = r;
    }

    @Override
    protected void handleString(String response, View view) {
        String result = "";
        Log.d("joinRequest", response);
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
