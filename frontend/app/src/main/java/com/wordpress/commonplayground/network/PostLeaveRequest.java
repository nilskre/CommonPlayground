package com.wordpress.commonplayground.network;

import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.wordpress.commonplayground.R;

import java.util.HashMap;

public class PostLeaveRequest extends VolleyStringTemplate {

    public PostLeaveRequest(Resources r) {
        super();
    }

    @Override
    protected void handleString(String response, View view) {
        HashMap<Integer,String> snackText = new HashMap <Integer, String> (){{
            put (-20, view.getResources().getString(R.string.leave_error));
            put (0, view.getResources().getString(R.string.leave_success));
        }};
        Snackbar.make(view, snackText.get(Integer.parseInt(response)), 5000).show();
    }
}
