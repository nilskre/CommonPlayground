package com.wordpress.commonplayground.network;


import android.support.design.widget.Snackbar;
import android.view.View;

import com.wordpress.commonplayground.R;

import java.util.HashMap;

public class PostMessageRequest extends VolleyStringTemplate {


    @Override
    protected void handleString(String response, View view) {

        HashMap<Integer, String> snackText = new HashMap<Integer, String>() {{
            put(-300, view.getResources().getString(R.string.send_message_failure));
            put(200, view.getResources().getString(R.string.send_message_success));
        }};
        Snackbar.make(view, snackText.get(Integer.parseInt(response)), 5000).show();


    }

}
