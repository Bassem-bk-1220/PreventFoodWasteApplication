package com.example.projetlicence.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlicence.Activity.ChatActivity;
import com.example.projetlicence.Modele.Users;
import com.example.projetlicence.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesAdapter extends RecyclerView.Adapter <MessagesAdapter.MyViewHolder> {
private List<Users>messageLists;
private final Context context;

    public MessagesAdapter(List<Users> messageLists, Context context) {
        this.messageLists = messageLists;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MessagesAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_adapter_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MessagesAdapter.MyViewHolder holder, int position) {
        Users user=messageLists.get(position);
        if(user.getProfileimage()!=null && !user.getProfileimage().isEmpty()){
            Picasso.get().load(user.getProfileimage()).placeholder(R.drawable.profile_picture).into(holder.profilePic);

        }
        holder.name.setText(user.getFullname());
        holder.lastMessages.setText(user.getLastMessage());
        if(user.getUnseemMessages() ==0){
            holder.unseeMessage.setVisibility(View.GONE);
            holder.lastMessages.setTextColor(Color.parseColor("#959595"));
        }else{
            holder.unseeMessage.setVisibility(View.VISIBLE);
            holder.unseeMessage.setText(user.getUnseemMessages()+"");
            holder.lastMessages.setTextColor(context.getResources().getColor(R.color.gris));
        }
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ChatActivity.class);
                intent.putExtra("fullname",user.getFullname());
                intent.putExtra("profileimage",user.getProfileimage());
                intent.putExtra("id_user", user.getId_user());

                context.startActivity(intent);

            }
        });

    }
    public void updateData(List<Users>messageLists){
        this.messageLists=messageLists;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return messageLists.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView profilePic;
        private TextView name,lastMessages, unseeMessage;
        private LinearLayout rootLayout;

        public MyViewHolder(@NonNull @NotNull View itemView) {

            super(itemView);
            profilePic=itemView.findViewById(R.id.imageview_Profile_pic);
            name=itemView.findViewById(R.id.textview_name);
            lastMessages=itemView.findViewById(R.id.textview_lastMessages);
            unseeMessage=itemView.findViewById(R.id.unseeMessages);
            rootLayout=itemView.findViewById(R.id.rootLayout);

        }
    }
}
