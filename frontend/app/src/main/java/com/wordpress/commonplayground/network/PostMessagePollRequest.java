package com.wordpress.commonplayground.network;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.wordpress.commonplayground.R;

public class PostMessagePollRequest extends VolleyStringTemplate {
    private final Activity activity;

    public PostMessagePollRequest( Activity activity) {
        super();
        this.activity = activity;
    }

    @Override
    protected void handleString(String response, View view) {
        Log.d("joinRequest", response);
        if ("true".equals(response)) {
            AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
            alertDialog.setMessage(view.getResources().getString(R.string.new_message));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, view.getResources().getString(R.string.confirm),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }
}
