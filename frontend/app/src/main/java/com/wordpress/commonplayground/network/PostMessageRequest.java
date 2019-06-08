package com.wordpress.commonplayground.network;


import android.app.Activity;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.wordpress.commonplayground.R;

import java.util.HashMap;

public class PostMessageRequest extends VolleyStringTemplate {
    Activity activity;

    public PostMessageRequest(Activity activity) {
        this.activity = activity;
    }


    @Override
    protected void handleString(String response, View view) {

        HashMap<Integer, String> snackText = new HashMap<Integer, String>() {{
            put(-300, view.getResources().getString(R.string.send_message_failure));
            put(200, view.getResources().getString(R.string.send_message_success));
        }};

        View focusview = activity.getCurrentFocus();
        if (focusview == null) {
            focusview = new View(activity);
        }
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(focusview.getWindowToken(), 0);

        Snackbar.make(activity.findViewById(android.R.id.content), snackText.get(Integer.parseInt(response)), 5000).show();
        if ("200".equals(response)){
            returnToMainActivity();
        }

    }

  private void returnToMainActivity() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.onBackPressed();
            }
        }, 2000);
    }

}
