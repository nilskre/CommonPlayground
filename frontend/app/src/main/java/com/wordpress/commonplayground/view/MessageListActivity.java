package com.wordpress.commonplayground.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.model.Message;
import com.wordpress.commonplayground.view.MessageDetailActivity;
import com.wordpress.commonplayground.view.MessagesAdapter;
import com.wordpress.commonplayground.viewmodel.MessagesViewModel;
import com.wordpress.commonplayground.viewmodel.SessionManager;

import java.util.List;

/**
 * An activity representing a list of Messages. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MessageDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MessageListActivity extends AppCompatActivity {
    private SessionManager session;
    private MessagesViewModel messagesViewModel;
    private RecyclerView rvMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        rvMessages = findViewById(R.id.message_list);
        assert rvMessages != null;
        setupRecyclerView();

        session = new SessionManager(getApplicationContext());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        observeChangesInSessionList();
    }

    private void observeChangesInSessionList() {
        messagesViewModel.getMessages().observe(this, new android.arch.lifecycle.Observer<List<Message>>() {
            @Override
            public void onChanged(@Nullable List<Message> messages) {
                Log.d("Observed: ", "SessionList changed");
                updateAndDisplayListData(messages);
            }
        });
    }

    private void updateAndDisplayListData(List<Message> messages) {
        rvMessages.setAdapter(new MessagesAdapter(messages));
        rvMessages.setLayoutManager(new LinearLayoutManager(this));
    }
}
