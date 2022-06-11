package com.example.projetlicence.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlicence.Activity.OrderReceviedDetailsActivity;
import com.example.projetlicence.Modele.Commande;
import com.example.projetlicence.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OrderReceviedAdapter extends RecyclerView.Adapter<OrderReceviedViewHolder>{
    private Context context;
    private List<Commande> list_commande;
    public OrderReceviedAdapter(Context context, List<Commande> commande){
        this.context=context;
        this.list_commande=commande;
    }
    @NonNull
    @NotNull
    @Override
    public OrderReceviedViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_recevied,parent,false);
        return new OrderReceviedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OrderReceviedViewHolder holder, int position) {
        Commande commande=list_commande.get(position);

        holder.textview_commande_number.setText(commande.getNr_commande());
        holder.textview_date_order.setText(commande.getDate());
        //holder.textview_prix_number.setText(commande.getPrix());

        holder.textview_commande_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OrderReceviedDetailsActivity.class);
                intent.putExtra("id_commande",commande.getId_commande());
                context.startActivity(intent);
            }
        });

        commande.getNr_commande();



    }

    @Override
    public int getItemCount() {

        return list_commande.size();
    }

}
