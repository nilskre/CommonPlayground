package com.wordpress.commonplayground.network;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.wordpress.commonplayground.R;

public class PostMessagePollRequest extends VolleyStringTemplate {
    private final Resources r;
    private final Activity activity;

    public PostMessagePollRequest(Resources r, Activity activity) {
        super();
        this.r = r;
        this.activity = activity;
    }

    @Override
    protected void handleString(String response, View view) {
        Log.d("joinRequest", response);
        if (response.equals("true")) {
            AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
            alertDialog.setMessage(r.getString(R.string.new_message));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, r.getString(R.string.confirm),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }
}
