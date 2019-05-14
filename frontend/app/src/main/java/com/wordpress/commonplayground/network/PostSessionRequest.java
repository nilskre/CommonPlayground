package com.wordpress.commonplayground.network;

import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.wordpress.commonplayground.R;

public class PostSessionRequest extends VolleyStringTemplate {

    public PostSessionRequest(Resources r) {
        super();
        this.r = r;
    }

    @Override
    protected void handleString(String response, View view) {
        Snackbar.make(view, r.getString(R.string.new_response_fine), 5000)
                .setAction("Action", null).show();
    }
}
