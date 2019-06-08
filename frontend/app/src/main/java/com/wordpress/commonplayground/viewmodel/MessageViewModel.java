package com.wordpress.commonplayground.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.wordpress.commonplayground.network.GetMessagesRequest;
import com.wordpress.commonplayground.network.VolleyStringNoResponse;

import java.util.HashMap;
import java.util.List;

public class MessageViewModel extends AndroidViewModel {

    private MutableLiveData<List<?>> inbox;

    public MessageViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<?>> getMessage(String userID) {
        String url = "getMyMessages?userID=" + userID;
        GetMessagesRequest request = new GetMessagesRequest();

        if(inbox == null){
            inbox = new MutableLiveData<>();
        }
        request.getJSONRequest(url, "Messages", this.getApplication(), inbox);
        return inbox;
    }


    public void deleteMessage(String userID, String messageID) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("messageID", messageID);

        VolleyStringNoResponse request = new VolleyStringNoResponse();
        request.stringRequest("removeMessage", "DeleteMessage", this.getApplication(), parameters);
    }

    public void answerRequest(String userID, String messageID, String requesterID, String joinAccepted) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("messageID", messageID);
        parameters.put("userIDToJoin", requesterID);
        parameters.put("joinAccepted", joinAccepted);

        Log.d("Host", parameters.get("userID"));
        Log.d("Joiner", parameters.get("userIDToJoin"));
        Log.d("messageID", parameters.get("messageID"));
        Log.d("accepted", parameters.get("joinAccepted"));

        VolleyStringNoResponse request = new VolleyStringNoResponse();
        request.stringRequest("joinResponse", "JoinResponse", this.getApplication(), parameters);
    }

}