package com.example.projetlicence.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlicence.Adapter.NotificationSellerViewHolder;
import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.R;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotificationSellerAdapter extends RecyclerView.Adapter<NotificationSellerViewHolder>{
    private Context context;
    private List<Products> ListProduct;
    private StorageReference storageReference;
    Products product;

    public NotificationSellerAdapter(Context context, List<Products>product) {
        this.context=context;
        this.ListProduct=product;
    }


    @NonNull
    @NotNull
    @Override
    public NotificationSellerViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification_seller,parent,false);
        return new NotificationSellerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NotificationSellerViewHolder holder, int position) {
        Products prod=ListProduct.get(position);
        holder.textv_name_product.setText(prod.getName_product());
        Picasso.get().load(prod.getProfileImage()).into(holder.imgview_logo_product);
        holder.textView_date_expiration.setText(prod.getDate_expiration());
    }

    @Override
    public int getItemCount() {
        return ListProduct.size();
    }
}
