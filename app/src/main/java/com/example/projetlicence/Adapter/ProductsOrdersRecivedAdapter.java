package com.example.projetlicence.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.Modele.Users;
import com.example.projetlicence.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductsOrdersRecivedAdapter extends RecyclerView.Adapter<ProductsOrdersRecivedViewHolder>{
    private Context context;
    private List<Products> list_product;

    public ProductsOrdersRecivedAdapter(Context context, List<Products> list_product) {
        this.context = context;
        this.list_product = list_product;
    }

    @NonNull
    @NotNull
    @Override
    public ProductsOrdersRecivedViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_recevied_seller,parent,false);
        return new ProductsOrdersRecivedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductsOrdersRecivedViewHolder holder, int position) {
        Products product=list_product.get(position);

        holder.textvie_Orange_juice_order.setText(product.getName_product());
        holder.textView_price_order.setText(product.getPrix());
        if(product.getProfileImage()!=null && !product.getProfileImage().isEmpty()) {
            Picasso.get().load(product.getProfileImage()).placeholder(R.drawable.profile_picture).into(holder.imageView_logo_order);
        }
    }

    @Override
    public int getItemCount() {
        return list_product.size();
    }
}
