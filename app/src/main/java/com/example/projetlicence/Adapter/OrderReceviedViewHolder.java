package com.example.projetlicence.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlicence.R;

import org.jetbrains.annotations.NotNull;

public class OrderReceviedViewHolder extends RecyclerView.ViewHolder{
    public TextView textview_commande_number,textview_prix_number,textview_date_order;
    public ConstraintLayout constraintLayout_order_recevied;

    public OrderReceviedViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        textview_commande_number= itemView.findViewById(R.id.txtv_commande_number);
        //textview_prix_number= itemView.findViewById(R.id.txtv_prix_number);
        textview_date_order= itemView.findViewById(R.id.txtv_date_order);
        constraintLayout_order_recevied= itemView.findViewById(R.id.item_order_recevied);

    }
}
