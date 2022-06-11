package com.example.projetlicence.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlicence.R;

import org.jetbrains.annotations.NotNull;

public class ProductsViewHolder extends RecyclerView.ViewHolder{
    public TextView textview_name_product,textview_date_expiration,textview_price,textView_quantity;
    public ImageView imageView_logo_product, imageView_update_product;
    public ConstraintLayout constraintLayout_products;

    public ProductsViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        textview_name_product= itemView.findViewById(R.id.txtv_Orange_juice);
        textview_date_expiration= itemView.findViewById(R.id.tv_date);
        textview_price= itemView.findViewById(R.id.tv_price);
        imageView_logo_product=itemView.findViewById(R.id.img_logo);
        textView_quantity=itemView.findViewById(R.id.tv_quantity);
        imageView_update_product=itemView.findViewById(R.id.btn_update_product);
        constraintLayout_products= itemView.findViewById(R.id.item_product);
    }
}
