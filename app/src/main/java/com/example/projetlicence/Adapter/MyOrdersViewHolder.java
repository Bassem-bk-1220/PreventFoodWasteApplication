package com.example.projetlicence.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlicence.R;

import org.jetbrains.annotations.NotNull;

public class MyOrdersViewHolder extends RecyclerView.ViewHolder{
    public TextView textview_name_product,textview_date_expiration,textview_price,textView_quantity,
            textView_moins,textView_plus;
    public ImageView imageView_logo_product_myorders,imageView_delete;
    public ConstraintLayout constraintLayout_myOrders;

    public MyOrdersViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        textview_name_product= itemView.findViewById(R.id.txtv_name_product);
        textview_price= itemView.findViewById(R.id.txtv_price);
        textView_quantity=itemView.findViewById(R.id.txtv_quantity);
        imageView_logo_product_myorders=itemView.findViewById(R.id.img_logo_product_myorders);
        textView_moins=itemView.findViewById(R.id.txtv_moins);
        textView_plus=itemView.findViewById(R.id.txtv_plus);
        imageView_delete=itemView.findViewById(R.id.imag_delete);
        constraintLayout_myOrders= itemView.findViewById(R.id.item_myorders);
    }
}
