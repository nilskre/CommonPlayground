package com.wordpress.commonplayground.network;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.wordpress.commonplayground.R;

public class PostSessionRequest extends VolleyStringTemplate {
    

    @Override
    protected void handleString(String response, View view) {
        Snackbar.make(view, view.getResources().getString(R.string.new_response_fine), 5000)
                .setAction("Action", null).show();
    }
}
