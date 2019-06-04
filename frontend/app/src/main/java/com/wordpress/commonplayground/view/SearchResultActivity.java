package com.wordpress.commonplayground.view;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.network.VolleyRequestQueue;
import com.wordpress.commonplayground.viewmodel.MainActivityViewModel;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeContainer;
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

        setUpSwipeToRefresh();
        rvSessions = findViewById(R.id.rvSessions);
        VolleyRequestQueue.getInstance(getApplicationContext());
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        observeChangesInSessionList();
    }

    private void setUpSwipeToRefresh() {
        swipeContainer = findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                observeChangesInSessionList();
                Snackbar.make(rvSessions, R.string.refreshed, 2000).show();
                swipeContainer.setRefreshing(false);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryLight,
                R.color.colorAccent);
    }

    private void observeChangesInSessionList() {
        mainActivityViewModel.getSessions(url).observe(this, new android.arch.lifecycle.Observer<List<?>>() {
            @Override
            public void onChanged(@Nullable List<?> sessions) {
                Log.d("Observed: ", "SessionList changed");
                Log.d("Observed: ", "No:" + sessions.size());
                updateAndDisplayListData(sessions);
                Log.d("url", url);
            }
        });
    }

    private void updateAndDisplayListData(List<?> sessions) {
        SessionsAdapter adapter = new SessionsAdapter(sessions);
        rvSessions.setAdapter(adapter);
        rvSessions.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
