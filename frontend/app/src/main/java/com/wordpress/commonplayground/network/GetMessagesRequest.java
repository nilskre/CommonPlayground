package com.wordpress.commonplayground.network;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.wordpress.commonplayground.model.Message;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class GetMessagesRequest extends VolleyJSONTemplate {

    @Override
    public void handleJSON(JSONArray response, MutableLiveData<List<?>> list) {
        List<Message> allMessagesTmpList = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            try {
                Message message;
                if (response.getJSONObject(i).getString("type").contentEquals("JoinRequest")) {
                    message = Message.parseJoinMessage(response.getJSONObject(i));
                    allMessagesTmpList.add(i, message);
                } else if (response.getJSONObject(i).getString("type").contentEquals("Info")) {
                    message = Message.parseGeneralMessage(response.getJSONObject(i));
                    allMessagesTmpList.add(i, message);
                }
            } catch (JSONException e) {
                Log.d("Parse.Error.Message", e.toString());
            }
        }
        list.setValue(allMessagesTmpList);
    }

}

