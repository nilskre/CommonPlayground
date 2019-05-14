package com.wordpress.commonplayground.network;

import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.wordpress.commonplayground.R;

public class PostLeaveRequest extends VolleyStringTemplate {
    private Resources r;

    public PostLeaveRequest(Resources r) {
        super();
        this.r = r;
    }

    @Override
    protected void handleString(String response, View view) {
        String result = "";
        Log.d("joinRequest", response);
        switch (Integer.parseInt(response)) {
            case -20:
                result = r.getString(R.string.leave_error);
                break;
            case 0:
                result = r.getString(R.string.leave_success);
                break;
            default:
                result = r.getString(R.string.new_error);
                break;
        }
        Snackbar.make(view, result, 5000).show();
    }
}
