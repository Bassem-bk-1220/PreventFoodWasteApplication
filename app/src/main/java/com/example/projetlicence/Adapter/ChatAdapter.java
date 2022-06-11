package com.example.projetlicence.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlicence.Modele.MessageUser;
import com.example.projetlicence.R;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {
    private List<MessageUser> messageUserList;
    private final Context context;

    public ChatAdapter( Context context,List<MessageUser> messageUserList) {
        this.messageUserList = messageUserList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_adapter_layout,parent,false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ChatViewHolder holder, int position) {
        MessageUser messageUser=messageUserList.get(position);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss aaa z");
        String dateTime = simpleDateFormat.format(calendar.getTime()).toString();
        if(messageUser.getSender().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){


            holder.myTime.setText(dateTime);
            holder.oppoMessage.setVisibility(View.INVISIBLE);
            holder.oppoTime.setVisibility(View.INVISIBLE);
            holder.myMessage.setText(messageUser.getMessage_user());
        }else{
            holder.oppoTime.setText(dateTime);
            holder.myMessage.setVisibility(View.INVISIBLE);
            holder.myTime.setVisibility(View.INVISIBLE);
            holder.oppoMessage.setText(messageUser.getMessage_user());
        }

    }

    @Override
    public int getItemCount() {
        return messageUserList.size();
    }
}
