package com.wordpress.commonplayground.view;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.network.VolleyRequestQueue;
import com.wordpress.commonplayground.viewmodel.MainActivityViewModel;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    private MainActivityViewModel mainActivityViewModel;
    private RecyclerView rvSessions;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString("url");
        }

        rvSessions = findViewById(R.id.rvSessions);
        VolleyRequestQueue.getInstance(getApplicationContext());
        mainActivityViewModel = new MainActivityViewModel(getApplication());
        observeChangesInSessionList();
    }

    private void observeChangesInSessionList() {
        mainActivityViewModel.getSessions(url).observe(this, new android.arch.lifecycle.Observer<List<?>>() {
            @Override
            public void onChanged(@Nullable List<?> sessions) {
                Log.d("Observed: ", "SessionList changed");
                Log.d("Observed: ", "No:" + sessions.size());
                updateAndDisplayListData(sessions);
            }
        });
    }

    private void updateAndDisplayListData(List<?> sessions) {
        SessionsAdapter adapter = new SessionsAdapter(sessions);
        rvSessions.setAdapter(adapter);
        rvSessions.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
