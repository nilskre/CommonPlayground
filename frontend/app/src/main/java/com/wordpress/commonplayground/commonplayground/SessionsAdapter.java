package com.wordpress.commonplayground.commonplayground;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class SessionsAdapter extends RecyclerView.Adapter<SessionsAdapter.SessionViewHolder> {

    private List<Session> activeSessions;

    // Pass in the sessions array into the constructor
    public SessionsAdapter(List<Session> sessions) {
        this.activeSessions = sessions;
    }

    @Override
    public SessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View sessionView = inflater.inflate(R.layout.item_session, parent, false);

        // Return a new holder instance
        SessionViewHolder viewHolder = new SessionViewHolder(sessionView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder( SessionViewHolder viewHolder, int position) {
        // Get the data model based on position
        Session session = activeSessions.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.sessionTextView;
        textView.setText(session.getTitle());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return activeSessions.size();
    }

    public static class SessionViewHolder extends RecyclerView.ViewHolder {
        public TextView sessionTextView;

        public SessionViewHolder(View itemView) {
            super(itemView);
            sessionTextView = (TextView) itemView.findViewById(R.id.session_id);
        }
    }
}

