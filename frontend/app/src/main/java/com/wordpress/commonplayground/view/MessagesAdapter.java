package com.wordpress.commonplayground.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.model.Message;
import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {

    private List<Message> inbox;

    // Pass in the sessions array into the constructor
    public MessagesAdapter(List<Message> inbox) {
        this.inbox = inbox;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View sessionView = inflater.inflate(R.layout.item_session, parent, false);

        // Return a new holder instance
        return new MessageViewHolder(context, sessionView);
    }


    @Override
    public void onBindViewHolder(MessageViewHolder viewHolder, int position) {
        // Get the data model based on position
        Message message = inbox.get(position);

        // Set item views based on your views and data model
        TextView titleTextView = viewHolder.titleTextView;
        titleTextView.setText(message.getTitle());
     //   TODO: getAuthor
    //    TextView authorTextView = viewHolder.authorTextView;
    //    authorTextView.setText(session.getGame());

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return inbox.size();
    }

    //TODO: Fit to Message Detail Views when available
    public class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titleTextView, dateTextView, gameTextView, placeTextView;
        private Context context;
        public MessageViewHolder(Context context, View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.session_title);
            gameTextView = itemView.findViewById(R.id.session_game);
            placeTextView = itemView.findViewById(R.id.session_place);
            dateTextView =  itemView.findViewById(R.id.session_date);
            this.context = context;
            itemView.setOnClickListener(this);
        }

      @Override
        public void onClick(View v) {
            int position = getAdapterPosition(); // gets item position
            long currentMessage = inbox.get(position).getId();
            Log.d("ClickTest", "Id:" + currentMessage);
            //TODO: Message Detail Page to open
           /* Intent openSessionDetailActivity = new Intent(context, SessionDetailActivity.class);
            Bundle b = new Bundle();
            b.putParcelableArrayList("Inbox", (ArrayList<? extends Parcelable>) inbox);
            openSessionDetailActivity.putExtras(b);
            openSessionDetailActivity.putExtra("Index", position);
            context.startActivity(openSessionDetailActivity);*/
        }
    }
}

