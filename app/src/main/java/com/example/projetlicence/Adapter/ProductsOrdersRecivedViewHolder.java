package com.example.projetlicence.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlicence.R;

import org.jetbrains.annotations.NotNull;

public class ProductsOrdersRecivedViewHolder extends RecyclerView.ViewHolder {
    public TextView textvie_Orange_juice_order,textview_quantity_order,textView_price_order;
    public ImageView imageView_logo_order;
    public ConstraintLayout constraint_layout_product_recevied;

    public ProductsOrdersRecivedViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        textvie_Orange_juice_order=itemView.findViewById(R.id.txtv_Orange_juice_order);
        //textview_quantity_order=itemView.findViewById(R.id.tv_quantity_order);
        textView_price_order=itemView.findViewById(R.id.tv_price_order);
        imageView_logo_order=itemView.findViewById(R.id.img_logo_order);
        constraint_layout_product_recevied=itemView.findViewById(R.id.constraint_layout_product_recevied);

    }
}
