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

public class SessionsAdapter extends RecyclerView.Adapter {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public Button messageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.session_id);
        }
    }

        private List<Session> activeSessions;
        // Pass in the sessions array into the constructor
        public SessionsAdapter(List<Session> sessions) {
            activeSessions = sessions;
        }

    @Override
    public SessionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_session, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

  @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
       // aus irgendeinem Grund akzeptiert er hier nur RecyclerView.ViewHolder und nicht den Selbstgebauten,
      // weshalb er die folgende onBindViewHolder nicht als implementierung der super Methode erkennt
    }

    public void onBindViewHolder(SessionsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Session session = activeSessions.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(session.getTitle());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return activeSessions.size();
    }
    }

