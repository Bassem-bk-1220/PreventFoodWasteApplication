package com.example.projetlicence.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlicence.R;

import org.jetbrains.annotations.NotNull;

public class ProductsFavoriteViewHolder extends RecyclerView.ViewHolder{
    public TextView textview_name_product,textview_date_expiration,textview_price;
    public ImageView imageView_logo_product, imageView_faourite;
    public ConstraintLayout constraintLayout_products_favorite;

    public ProductsFavoriteViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        textview_name_product= itemView.findViewById(R.id.txtv_Orange_juice);
        textview_date_expiration= itemView.findViewById(R.id.tv_date);
        textview_price= itemView.findViewById(R.id.tv_price);
        imageView_logo_product=itemView.findViewById(R.id.img_logo);
        constraintLayout_products_favorite= itemView.findViewById(R.id.item_favorite_product);
        imageView_faourite=itemView.findViewById(R.id.imag_faourite);
    }
}
