package com.example.projetlicence.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetlicence.Modele.Products;
import com.example.projetlicence.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersViewHolder>{
    private Context context;
    private List<Products> ListProduct;
    int value=1;
    public MyOrdersAdapter(Context context, List<Products>product){
        this.context=context;
        this.ListProduct=product;
    }


    @NonNull
    @NotNull
    @Override
    public MyOrdersViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myorders_client,parent,false);
        return new MyOrdersViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull MyOrdersViewHolder holder, int position) {
        Products products=ListProduct.get(position);

        holder.textview_name_product.setText(products.getName_product());
        //holder.textView_quantity.setText("1");

        //holder.textview_price.setText(products.getPrix());

        if(products.getProfileImage()!=null) {
            Picasso.get().load(products.getProfileImage()).into(holder.imageView_logo_product_myorders);
        }else{
            holder.imageView_logo_product_myorders.setImageResource(R.drawable.profile_picture);
        }

        holder.textView_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + 1;
                holder.textView_quantity.setText("" + value);
                holder.textview_price.setText(String.valueOf(Double.parseDouble(products.getPrix()) * value));
            }
        });

        holder.textView_moins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value - 1;
                holder.textView_quantity.setText("" + value);
                holder.textview_price.setText(String.valueOf(Double.parseDouble(products.getPrix()) * value));
            }
        });

        holder.imageView_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.constraintLayout_myOrders.removeView(i);
               /* for(Products item : ListProduct){
                    ListProduct.remove(item);
                }*/

            }
        });


    }

    @Override
    public int getItemCount() {

        return ListProduct.size();
    }

}
