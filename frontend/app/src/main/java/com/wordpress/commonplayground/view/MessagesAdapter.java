package com.wordpress.commonplayground.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;
import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.model.Message;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {

    private List<Message> inbox;
    private final ExpansionLayoutCollection expansionsCollection = new ExpansionLayoutCollection();

    // Pass in the sessions array into the constructor
    public MessagesAdapter(List<Message> inbox ) {
        this.inbox = inbox;
        expansionsCollection.openOnlyOne(true);
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MessageViewHolder.buildFor(parent);
    }


    @Override
    public void onBindViewHolder(MessageViewHolder viewHolder, int position) {
        Message message = inbox.get(position);
        // Get the data model based on position
        viewHolder.bind(inbox.get(position));
        TextView titleTextView = viewHolder.titleTextView;
        titleTextView.setText(message.getTitle());
        TextView authorTextView = viewHolder.authorTextView;
        //authorTextView.setText(message.getTitle());
        TextView descriptionTextView = viewHolder.descriptionTextView;
        descriptionTextView.setText(message.getDescription());

        expansionsCollection.add(viewHolder.getExpansionLayout());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return inbox.size();
    }

    //TODO: Fit to Message Detail Views when available
    public final static class MessageViewHolder extends RecyclerView.ViewHolder {
        private static final int LAYOUT = R.layout.expansion_panel_recycler_cell;
        public TextView titleTextView, authorTextView, descriptionTextView;
        ExpansionLayout expansionLayout;


        public static MessageViewHolder buildFor(ViewGroup viewGroup){
            return new MessageViewHolder (LayoutInflater.from(viewGroup.getContext()).inflate(LAYOUT, viewGroup, false));
        }

        public MessageViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.message_title);
            authorTextView =  itemView.findViewById(R.id.message_author);
            descriptionTextView = itemView.findViewById(R.id.message_content);
            expansionLayout = itemView.findViewById(R.id.expansionLayout);
        }

        public void bind(Object object){
            expansionLayout.collapse(false);
        }

        public ExpansionLayout getExpansionLayout() {
            return expansionLayout;
        }
    }
}

