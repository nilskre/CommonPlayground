package com.wordpress.commonplayground.network;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.wordpress.commonplayground.R;

import java.util.HashMap;

public class PostLeaveRequest extends VolleyStringTemplate {

    @Override
    protected void handleString(String response, View view) {
        HashMap<Integer,String> snackText = new HashMap <Integer, String> (){{
            put (-20, view.getResources().getString(R.string.leave_error));
            put (0, view.getResources().getString(R.string.leave_success));
            put(1, view.getResources().getString(R.string.leave_retracted));
        }};
        Snackbar.make(view, snackText.get(Integer.parseInt(response)), 5000).show();
    }
}
