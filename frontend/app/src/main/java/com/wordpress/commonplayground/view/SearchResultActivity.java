package com.wordpress.commonplayground.view;

import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.model.Session;
import com.wordpress.commonplayground.network.VolleyRequestQueue;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    private RecyclerView rvSessions;
    private List<Session> sessionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sessionList = extras.getParcelableArrayList("Sessions");
        }

        rvSessions = findViewById(R.id.rvSessions);
        VolleyRequestQueue.getInstance(getApplicationContext());
        if (sessionList != null) {
            displayListData(sessionList);
        }
    }

    private void displayListData(List<?> sessions) {
        SessionsAdapter adapter = new SessionsAdapter(sessions);
        rvSessions.setAdapter(adapter);
        rvSessions.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
