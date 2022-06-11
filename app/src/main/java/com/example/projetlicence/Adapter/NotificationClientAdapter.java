package com.example.projetlicence.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.R;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotificationClientAdapter extends RecyclerView.Adapter<NotificationClientViewHolder>{
    private Context context;
    private List<Products> ListProduct;
    private StorageReference storageReference;
    Products product;

    public NotificationClientAdapter(Context context, List<Products>product) {
        this.context=context;
        this.ListProduct=product;
    }


    @NonNull
    @NotNull
    @Override
    public NotificationClientViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification_client,parent,false);
        return new NotificationClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NotificationClientViewHolder holder, int position) {
        Products prod=ListProduct.get(position);
        holder.textv_name_product.setText(prod.getName_product());
        Picasso.get().load(prod.getProfileImage()).into(holder.imgview_logo_product);
    }

    @Override
    public int getItemCount() {
        return ListProduct.size();
    }
}
