package com.example.projetlicence.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlicence.R;

import org.jetbrains.annotations.NotNull;


public class NotificationClientViewHolder extends RecyclerView.ViewHolder{
    public TextView textv_name_product,textv_message_new_prod;
    public ImageView imgview_logo_product,imgview_icon_notif, imgview_ligne;
    public ConstraintLayout constLay_notification_client;
    public NotificationClientViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        textv_name_product=itemView.findViewById(R.id.txtv_name_product);
        textv_message_new_prod=itemView.findViewById(R.id.tv_message_new_prod);
        imgview_logo_product=itemView.findViewById(R.id.img_logo_product);
        imgview_icon_notif=itemView.findViewById(R.id.img_icon_notif);
        imgview_ligne=itemView.findViewById(R.id.img_ligne);
        constLay_notification_client=itemView.findViewById(R.id.item_notification_client);

    }
}
