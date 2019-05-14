package com.wordpress.commonplayground.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.wordpress.commonplayground.network.GetSessionRequest;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<?>> activeSessions;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<?>> getSessions(String api) {
        GetSessionRequest request = new GetSessionRequest();
        if(activeSessions == null){
            activeSessions = new MutableLiveData<>();
        }
        request.getJSONRequest(api, "Sessions", this.getApplication(), activeSessions);
        return activeSessions;
    }
}