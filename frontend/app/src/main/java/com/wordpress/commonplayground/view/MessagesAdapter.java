package com.wordpress.commonplayground.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;
import com.wordpress.commonplayground.R;
import com.wordpress.commonplayground.model.Message;
import com.wordpress.commonplayground.viewmodel.MessageViewModel;
import com.wordpress.commonplayground.viewmodel.SessionManager;

import java.util.List;
import java.util.Objects;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {

    private List<?> inbox;
    private final ExpansionLayoutCollection expansionsCollection = new ExpansionLayoutCollection();
    private SessionManager session;
    private MessageViewModel viewModel;
    private RecyclerView parent;

    public MessagesAdapter(List<?> inbox, SessionManager session, MessageViewModel viewModel, RecyclerView recyclerView) {
        this.inbox = inbox;
        this.session = session;
        this.viewModel = viewModel;
        parent = recyclerView;
        expansionsCollection.openOnlyOne(true);
    }

    @Override
    @NonNull
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MessageViewHolder.buildFor(parent);
    }


    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder viewHolder, int position) {
        Message message = (Message) inbox.get(position);
        TextView titleTextView = viewHolder.titleTextView;
        TextView descriptionTextView = viewHolder.descriptionTextView;
        TextView authorTextView = viewHolder.authorTextView;
        Button deleteButton = viewHolder.deleteButton;
        Button acceptButton = viewHolder.acceptButton;
        Button rejectButton = viewHolder.rejectButton;

        authorTextView.setText(message.getAuthor());
        titleTextView.setText(message.getTitle());
        descriptionTextView.setText(message.getDescription());

        setUpButtons(viewHolder, position, deleteButton, acceptButton, rejectButton);

        expansionsCollection.add(viewHolder.getExpansionLayout());
        viewHolder.bind(inbox.get(position));
    }

    private void setUpButtons(MessageViewHolder viewHolder, int position, Button deleteButton, Button acceptButton, Button rejectButton) {
        int pos = viewHolder.getAdapterPosition();
        String passMID = Long.toString(((Message) inbox.get(pos)).getId());
        String passUID = session.getUserDetails().get(SessionManager.KEY_ID);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteMessage(passUID, passMID);
                inbox.remove(pos);
                Objects.requireNonNull(parent.getAdapter()).notifyItemRemoved(pos);
            }
        });

        if (((Message) inbox.get(position)).getType().contentEquals("JoinRequest")) {
            acceptButton.setVisibility(View.VISIBLE);
            acceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.answerRequest(passUID, passMID, "true");
                }
            });
            rejectButton.setVisibility(View.VISIBLE);
            rejectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.answerRequest(passUID, passMID, "false");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return inbox.size();
    }

    public final static class MessageViewHolder extends RecyclerView.ViewHolder {
        private static final int LAYOUT = R.layout.expansion_panel_recycler_cell;
        public TextView titleTextView, authorTextView, descriptionTextView;
        public Button deleteButton, acceptButton, rejectButton;
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
            deleteButton = itemView.findViewById(R.id.ButtonDeleteMessage);
            acceptButton = itemView.findViewById(R.id.ButtonAcceptJoin);
            rejectButton = itemView.findViewById(R.id.ButtonRejectJoin);
        }

        public void bind(Object object){
            expansionLayout.collapse(false);
        }

        public ExpansionLayout getExpansionLayout() {
            return expansionLayout;
        }
    }

}

