package com.example.projetlicence.Adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlicence.R;

import org.jetbrains.annotations.NotNull;

public class ChatViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout oppoLayout, myLayout;
    public TextView oppoMessage, myMessage;
    public TextView oppoTime, myTime;

    public ChatViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        oppoLayout=itemView.findViewById(R.id.oppoLayout);
        myLayout=itemView.findViewById(R.id.myLayout);
        oppoMessage=itemView.findViewById(R.id.oppoMessage);
        myMessage=itemView.findViewById(R.id.myMessage);
        oppoTime=itemView.findViewById(R.id.oppoMsgTime);
        myTime=itemView.findViewById(R.id.myMsgTime);
    }
}
